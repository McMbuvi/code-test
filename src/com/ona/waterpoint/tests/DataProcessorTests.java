/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ona.waterpoint.tests;

import com.ona.waterpoint.DataProcessor;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class DataProcessorTests {
  
    @Test
    public void testEmptyJSONArray() {
        DataProcessor processor = new DataProcessor();
        processor.processWaterPoints("[]");
        JSONObject result = processor.createReport();
        System.out.println(result.toString());
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 0);
        assertEquals(result.get(DataProcessor.NUMBER_WATER_POINTS).toString(), "{}");
        assertEquals(result.get(DataProcessor.COMMUNITY_RANKING).toString(), "{}");
    }
    
    @Test
    public void testOneJSONWaterPoint() {
        DataProcessor processor = new DataProcessor();
        processor.processWaterPoints("[]");
        JSONObject result = processor.createReport();
        System.out.println(result.toString());
        assertEquals(result.get(DataProcessor.NUMBER_FUNCTIONAL), 0);
        assertEquals(result.get(DataProcessor.NUMBER_WATER_POINTS).toString(), "{}");
        assertEquals(result.get(DataProcessor.COMMUNITY_RANKING).toString(), "{}");
    }
    
    @Test
    public void testFunctionalWaterPoints() {
        
    }
    
    @Test
    public void testBrokenPercentageRank() {
        
    }
    
    @Test
    public void testWaterPointsPerCommunity() {
        
    }
    
    @Test
    public void testDataSetJson() throws Exception {
        DataProcessor processor = new DataProcessor();
        processor.calculate("https://raw.githubusercontent.com/onaio/ona-tech/master/data/water_points.json");
    }
}
