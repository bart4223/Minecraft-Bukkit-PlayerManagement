/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement.SocialPoint;

import com.mahn42.framework.PlayerManager;
import com.mahn42.framework.SocialPointEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author Nils
 */
public class SocialPointListenerWarning implements Listener {
    
   public SocialPointListenerWarning(SocialPointManager aSocialPointManager) {
       fSocialPointManager = aSocialPointManager;
   }    

   @EventHandler
    public void socialpointwarningchanged(SocialPointEvent aEvent) {
        PlayerManager.SocialPoint lSP = aEvent.getSocialPoint(); 
        if (lSP.getName().equals("warning")) {
            Integer lValue;
            String lName = aEvent.getSocialPointHistory().getChargePlayerName();
            Player lPlayer = fSocialPointManager.Plugin.getServer().getPlayer(lName);
            lValue = (Integer)fSocialPointManager.getSocialPointTypePropValue("warning", "PointsBeforeBann");
            if (lValue != null && lSP.getAmount() >= lValue) {
                lName = aEvent.getSocialPointHistory().getChargePlayerName();
                fSocialPointManager.Plugin.getServer().broadcastMessage(ChatColor.RED.toString() + "Warnings of Player: " + lName + " exceeded, he was BANNED!");
                lPlayer.setBanned(true);
                lPlayer.kickPlayer(ChatColor.RED.toString() + "Warnings exceeded, you was BANNED!");
            }
            else {
                lValue = (Integer)fSocialPointManager.getSocialPointTypePropValue("warning", "PointsBeforeKick");
                if (lValue != null && lSP.getAmount() >= lValue) {
                    fSocialPointManager.Plugin.getServer().broadcastMessage(ChatColor.RED.toString() + "Warnings of Player: " + lName + " exceeded, he was KICKED!");
                    lPlayer.kickPlayer(ChatColor.RED.toString() + "Warnings exceeded, you was KICKED!");
                }
            }
        }
    }

   protected SocialPointManager fSocialPointManager;
   
}
