package com.ona.waterpoint;

/**
 *
 * @author Isaac Mwongela(mwongelaima@gmail.com)
 */
public class WaterPoint {
    private boolean functional;
    private String community;

    /**
     * Constructor - Initializes the class variables.
     * 
     * @param functional sets the state of the tap whether functional or not.
     * @param community sets the name of the community.
     */
    public WaterPoint(boolean functional, String community) {
        this.functional = functional;
        this.community = community;      
    }

    /**
     * @return true if the water point is functional or false otherwise.
     */
    public boolean isFunctional() {
        return functional;
    }

    /**
     * @param functional is used to set whether the water point is functional or
     * not.
     */
    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    /**
     * @return the name of the community.
     */
    public String getCommunity() {
        return community;
    }

    /**
     * @param community the new community name to set.
     */
    public void setCommunity(String community) {
        this.community = community;
    }
}
