/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ona.waterpoint.tests;

import com.ona.waterpoint.WaterPoint;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class WaterPointTests {
    public static final String JAGANATH = "Jaganath";
    public static final String PRADESH = "Pradesh";

    @Test
    public void testCreateWaterPoint() {
        WaterPoint wp = new WaterPoint(true, JAGANATH);
        assertTrue(wp.isFunctional());
        assertEquals(wp.getCommunity(), JAGANATH);
    }
  
    @Test
    public void testWaterPointAfterUpdate() {
        WaterPoint wp = new WaterPoint(true, JAGANATH);
        wp.setCommunity(PRADESH);
        assertEquals(wp.getCommunity(), PRADESH);
        wp.setFunctional(false);
        assertTrue(!wp.isFunctional());
    }
}
