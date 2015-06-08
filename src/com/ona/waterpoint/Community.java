/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ona.waterpoint;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class Community implements Comparable<Community> {

    private int totalWaterPoints;
    private int brokenWaterPoints;
    private String communityName;

    /**
     * 
     * @param name
     * @param totalWaterPoints
     * @param brokenWaterPoints 
     */
    public Community(String name, int totalWaterPoints, int brokenWaterPoints) {
        this.communityName = name;
        this.totalWaterPoints = totalWaterPoints;
        this.brokenWaterPoints = brokenWaterPoints;
    }

    /**
     * @return the totalWaterPoints for a community.
     */
    public int getTotalWaterPoints() {
        return totalWaterPoints;
    }

    /**
     * @param totalWaterPoints the totalWaterPoints to set
     */
    public void setTotalWaterPoints(int totalWaterPoints) {
        this.totalWaterPoints = totalWaterPoints;
    }

    /**
     * @return the brokenWaterPoints
     */
    public int getBrokenWaterPoints() {
        return brokenWaterPoints;
    }

    /**
     * @param brokenWaterPoints the brokenWaterPoints to set
     */
    public void setBrokenWaterPoints(int brokenWaterPoints) {
        this.brokenWaterPoints = brokenWaterPoints;
    }
    
    /**
     * @return the communityName
     */
    public String getCommunityName() {
        return communityName;
    }

    /**
     * @param communityName the communityName to set
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
    
    /**
     * @return percentage of broken water points
     */
    public double getBrokenPercentage() {
        return (double) this.brokenWaterPoints / this.totalWaterPoints;
    }

    /**
     * @param comp the community to compare the percentage of broken water
     * points with this.
     * @return a comparative integer value for sorting.
     */
    @Override
    public int compareTo(Community comp) {
        if (getBrokenPercentage() == comp.getBrokenPercentage()) {
            return 0;
        } else if (getBrokenPercentage() > comp.getBrokenPercentage()) {
            return 1;
        } else {
            return -1;
        }
    }
}
