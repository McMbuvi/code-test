package com.ona.waterpoint.tests;

import com.ona.waterpoint.DataProcessor;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class DataProcessorTests {
    public static final String BECHINSA = "Bechinsa";
    public static final String CHANPOLINSA = "Chanpolinsa";
    public static final String DORINSA = "Dorinsa";
    public static final String JAGANATH = "Jaganath";
    public static final String JINIENSA = "Jiniensa";
    public static final String NABULUGU = "Nabulugu";
    public static final String PRADESH = "Pradesh";
    public static final String SELINVOYA = "Selinvoya";   
    public static final String ZANGU_VUGA = "Zangu-Vuga";
    public static final String ZUA = "Zua";
    public static final String ZUEDEMA = "Zuedema";  

    /**
     * Test a dataset with no water points.
     */
    @Test
    public void testEmptyJSONArray() {
        DataProcessor processor = new DataProcessor();
        processor.processWaterPoints("[]");
        JSONObject result = processor.createReport();
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 0);
        assertEquals(result.get(DataProcessor.NUMBER_WATER_POINTS).toString(), "{}");
        assertEquals(result.get(DataProcessor.COMMUNITY_RANKING).toString(), "{}");
    }

    /**
     * Test a data set with water points from a single community.
     */
    @Test
    public void testOneCommunity() {
        DataProcessor processor = new DataProcessor();
        processor.processWaterPoints(
                "[{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Nabulugu\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"date\":\"2012-11-01\",\"water_point_image\":\"1351762272917.jpg\",\"water_point_geocode\":\"10.31826864 -0.66363612 171.5 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"community\",\"_geolocation\":[\"10.31826864\",\"-0.66363612\"],\"_id\":381742,\"_attachments\":[\"north_ghana/attachments/1351762272917.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"yes\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"international\",\"_uuid\":\"ec954092ff574c6897ccf92113ae4dae\",\"locations_wards\":\"west_mamprusi\",\"other_point_1km\":\"yes\",\"end\":\"2012-11-01T09:33:03.725Z\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"water_lift_mechanism_type\":\"hand_pump\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_mechanism_plate\":\"no\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Nabulugu\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Abdulai\",\"_submission_time\":\"2012-11-13T07:18:38\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:29:40.556Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"}]");
        JSONObject result = processor.createReport();
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 2);
        JSONObject numWaterPoints = (JSONObject) result.get(DataProcessor.NUMBER_WATER_POINTS);
        assertEquals(numWaterPoints.get(NABULUGU), 2);
        JSONObject communityRanking = (JSONObject) result.get(DataProcessor.COMMUNITY_RANKING);
        assertEquals(communityRanking.get(NABULUGU), 1);
    }

    /**
     * Test a dataset with several communities.
     */
    @Test
    public void testCommunitiesDataSet() {
        DataProcessor processor = new DataProcessor();
        processor.processWaterPoints(
                "[{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Nabulugu\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Pradesh\",\"water_functioning\":\"no\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Nabulugu\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Jaganath\",\"water_functioning\":\"no\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Jaganath\",\"water_functioning\":\"no\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"water_point_image\":\"1351761199568.jpg\",\"date\":\"2012-11-01\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"water_point_geocode\":\"10.31986135 -0.66303381 171.6999969482422 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"individual\",\"_geolocation\":[\"10.31986135\",\"-0.66303381\"],\"_id\":381737,\"_attachments\":[\"north_ghana/attachments/1351761199568.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"no\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"community\",\"_uuid\":\"b6680bfb18204d16bfd4c2d5e6732399\",\"locations_wards\":\"west_mamprusi\",\"end\":\"2012-11-01T09:15:15.371Z\",\"other_point_1km\":\"yes\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Pradesh\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Sulemana\",\"_submission_time\":\"2012-11-13T07:17:55\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:12:34.642Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"},"+
                "{\"_deleted_at\":null,\"signal\":\"high\",\"formhub/uuid\":\"4d41d54d134c4bfa9078571addd819b9\",\"date\":\"2012-11-01\",\"water_point_image\":\"1351762272917.jpg\",\"water_point_geocode\":\"10.31826864 -0.66363612 171.5 5.0\",\"water_used_season\":\"year_round\",\"deviceid\":\"355047040123780\",\"water_manager\":\"community\",\"_geolocation\":[\"10.31826864\",\"-0.66363612\"],\"_id\":381742,\"_attachments\":[\"north_ghana/attachments/1351762272917.jpg\"],\"animal_point\":\"yes\",\"research_asst_name\":\"H.M\",\"water_lift_mechanism\":\"yes\",\"grid\":\"grid_within_500_m\",\"water_developer\":\"international\",\"_uuid\":\"ec954092ff574c6897ccf92113ae4dae\",\"locations_wards\":\"west_mamprusi\",\"other_point_1km\":\"yes\",\"end\":\"2012-11-01T09:33:03.725Z\",\"_status\":\"submitted_via_web\",\"respondent\":\"community\",\"water_lift_mechanism_type\":\"hand_pump\",\"_xform_id_string\":\"_08_Water_points_CV\",\"water_mechanism_plate\":\"no\",\"water_source_type\":\"borehole\",\"animal_number\":\"50_to_500\",\"districts_divisions\":\"northern\",\"communities_villages\":\"Nabulugu\",\"water_functioning\":\"yes\",\"enum_id_1\":\"5\",\"water_manager_name\":\"Abdulai\",\"_submission_time\":\"2012-11-13T07:18:38\",\"road_available\":\"no\",\"start\":\"2012-11-01T09:29:40.556Z\",\"water_point_id\":\"xxx\",\"_bamboo_dataset_id\":\"\",\"water_connected\":\"no\",\"water_point_condition\":\"functioning\",\"water_pay\":\"no\"}]");
        JSONObject result = processor.createReport();
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 4);
        JSONObject numWaterPoints = (JSONObject) result.get(DataProcessor.NUMBER_WATER_POINTS);
        assertEquals(numWaterPoints.get(NABULUGU), 3);
        assertEquals(numWaterPoints.get(PRADESH), 2);
        assertEquals(numWaterPoints.get(JAGANATH), 2);
        JSONObject communityRanking = (JSONObject) result.get(DataProcessor.COMMUNITY_RANKING);
        assertEquals(communityRanking.get(JAGANATH), 1);
        assertEquals(communityRanking.get(PRADESH), 2);
        assertEquals(communityRanking.get(NABULUGU), 3);
    }
    
    /**
     * Test with the provided json dataset url.
     * 
     * @throws Exception if there network error reading from url.
     */
    @Test
    public void testUrlDataSetJson() throws Exception {
        DataProcessor processor = new DataProcessor();
        processor.calculate("https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json");
        JSONObject result = processor.createReport();
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 623);
        JSONObject numWaterPoints = (JSONObject) result.get(DataProcessor.NUMBER_WATER_POINTS);
        assertEquals(numWaterPoints.get(BECHINSA), 26);
        assertEquals(numWaterPoints.get(JINIENSA), 1);
        assertEquals(numWaterPoints.get(ZUEDEMA), 18);
        JSONObject communityRanking = (JSONObject) result.get(DataProcessor.COMMUNITY_RANKING);
        assertEquals(communityRanking.get(CHANPOLINSA), 14);
        assertEquals(communityRanking.get(DORINSA), 29);
        assertEquals(communityRanking.get(SELINVOYA), 27);
        assertEquals(communityRanking.get(ZANGU_VUGA), 21);
        assertEquals(communityRanking.get(ZUA), 22);
    }
}
