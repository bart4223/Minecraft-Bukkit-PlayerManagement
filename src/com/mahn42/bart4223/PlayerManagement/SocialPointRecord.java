/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.PlayerManager;

/**
 *
 * @author Nils
 */
public class SocialPointRecord implements PlayerManager.SocialPoint{

    public SocialPointRecord(String aPointType, int aTotalPoints){
        fPointType = aPointType;
        fTotalPoints = aTotalPoints;
    }
    
    @Override
    public String getName() {
        return fPointType;
    }

    @Override
    public int getAmount() {
        return fTotalPoints;
    }
    
    protected String fPointType;
    protected int fTotalPoints;
    
}
