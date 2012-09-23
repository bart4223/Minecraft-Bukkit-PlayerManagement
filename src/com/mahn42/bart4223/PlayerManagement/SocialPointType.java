/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Nils
 */
public class SocialPointType {
    
    public String Name;

    public SocialPointType(String aName) {
        Name = aName;
        fProps = new ArrayList<SocialPointProperty>();
    }
    
    public void AddProp(String aName, Object aValue) {
        SocialPointProperty lSPP = new SocialPointProperty(aName, aValue);
        fProps.add(lSPP);
    }
    
    public SocialPointProperty GetProp(String aName) {
        Iterator<SocialPointProperty> lItr = fProps.iterator();
        while (lItr.hasNext()) {
            SocialPointProperty lSPP = lItr.next();
            if (lSPP.Name.equals(aName)) {
                return lSPP;
            }
        }
        return null;
    }
    
    public Object GetPropValue(String aName) {
        Iterator<SocialPointProperty> lItr = fProps.iterator();
        while (lItr.hasNext()) {
            SocialPointProperty lSPP = lItr.next();
            if (lSPP.Name.equals(aName)) {
                return lSPP.Value;
            }
        }
        return null;
    }
    
    public int GetPropsCount() {
        return fProps.size();
    }
    
    protected ArrayList<SocialPointProperty> fProps;

}
