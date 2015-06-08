package com.ona.waterpoint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class DataProcessor {

    public static final String COMMUNITIES_VILLAGES = "communities_villages";
    public static final String COMMUNITY_RANKING = "community_ranking";
    public static final String NUMBER_FUNCTIONAL = "number_functional";
    public static final String NUMBER_WATER_POINTS = "number_water_points";
    public static final String WATER_FUNCTIONING = "water_functioning";
    public static final String WATER_FUNCTIONING_YES = "yes";

    /**
     * A mapping for community name to the community water point resources info
     * (facilitates fast retrieval and update of community resources).
     */
    Map<String, Community> communitiesWaterPoints;
    
    /**
     * The total number of functional water points in all communities.
     */
    private int functionalWaterPoints = 0;

    /**
     * Constructor. Initializes resource map.
     */
    public DataProcessor() {
        communitiesWaterPoints = new HashMap<String, Community>();
    }

    /**
     * Takes a url of water points json for processing and returns a result
     * object.
     * 
     * @param url the url to the json file containing water points data set.
     * @return json object containing details on water points.
     * @throws Exception when there is error reading data from url.
     */
    public JSONObject calculate(String url) throws Exception {
        try {
            String waterPoints = readJSONUrl(url);
            processWaterPoints(waterPoints);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return createReport();
    }
    
    /**
     * Reads water points data set from the provided url into a String object.
     * 
     * @param urlString the url to the json water points data.
     * @return a String containing the water points data.
     * @throws Exception if error reading from url.
     */
    private String readJSONUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Loops through the water points retrieving the relevant information for
     * generating results.
     * 
     * @param waterPoints a String containing all the water points information.
     */
    public void processWaterPoints(String waterPoints) {
        boolean isFunctional;
        String communityName;
        String waterFunctioning;
        WaterPoint waterPoint;
        JSONArray json = new JSONArray(waterPoints);
        for (int i = 0; i < json.length(); i++) {
            JSONObject waterPointObject = (JSONObject) json.getJSONObject(i);
            communityName = (String) waterPointObject.get(COMMUNITIES_VILLAGES);
            waterFunctioning = (String) waterPointObject.get(WATER_FUNCTIONING);
            isFunctional = waterFunctioning.trim().equals(WATER_FUNCTIONING_YES) ? true : false;
            waterPoint = new WaterPoint(isFunctional, communityName);
            addWaterPoint(waterPoint);
        }
    }
    
    /**
     * Puts a new water point into the HashMap containing water point resources
     * for each community. If the community is already in the Map, the total
     * water points are incremented otherwise a new community is created with 
     * the initial water point provided.
     * 
     * @param waterPoint the new water point to be added.
     */
    private void addWaterPoint(WaterPoint waterPoint) {
        String community = waterPoint.getCommunity();
        Community cwp;
        if (communitiesWaterPoints.containsKey(community)) {
            cwp = communitiesWaterPoints.get(community);

            //Increment the total number of water points for the community.
            int totalWP = cwp.getTotalWaterPoints();
            cwp.setTotalWaterPoints(totalWP + 1);
        } else {
            cwp = new Community(community, 1, 0);
        }

        if (waterPoint.isFunctional()) {//increment functional water points.
            functionalWaterPoints++;
        } else {//increment broken water points for community.
            int brokenWP = cwp.getBrokenWaterPoints();
            cwp.setBrokenWaterPoints(brokenWP + 1);
        }
        communitiesWaterPoints.put(community, cwp);
    }

    /**
     * Returns the number of functional water points in all communities.
     * 
     * @return the number of functional water points.
     */
    public int getFunctionalWaterPoints() {
        return functionalWaterPoints;
    }

    /**
     * Sets the number of functional water points in all communities.
     * 
     * @param functionalWaterPoints the functionalWaterPoints to set.
     */
    public void setFunctionalWaterPoints(int functionalWaterPoints) {
        this.functionalWaterPoints = functionalWaterPoints;
    }

    /**
     * Creates a report on the number of functional water points, the number of
     * water points per community and the ranking of each community based on the
     * percentage of the broken water points.
     * 
     * @return a JSON object.
     */
    public JSONObject createReport() {
        //The object to return.
        JSONObject result = new JSONObject();

        //Add the number of functional water points to the result object.
        result.put(NUMBER_FUNCTIONAL, getFunctionalWaterPoints());

        //Retrieve and add the total number of water point for each community.
        List<Community> cwps = new ArrayList<Community>(communitiesWaterPoints.values());
        JSONObject cps = new JSONObject();
        for (Community cwp : cwps) {
            cps.append(cwp.getCommunityName(), cwp.getTotalWaterPoints());
        }
        result.put(NUMBER_WATER_POINTS, cps);

        //Sort the communities on percentage of broken water points.
        Collections.sort(cwps);
        Collections.reverse(cwps);
        JSONObject cpsRank = new JSONObject();
        int rank = 1;
        for (Community cwp : cwps) {
            cpsRank.append(cwp.getCommunityName(), rank);
            rank++;
        }
        result.put(COMMUNITY_RANKING, cpsRank);
        return result;
    }
}
