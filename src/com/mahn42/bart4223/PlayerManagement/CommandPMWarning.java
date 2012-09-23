/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.Framework;
import com.mahn42.framework.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Nils
 */
public class CommandPMWarning implements CommandExecutor{
    
    public SocialPointManager SocialPointManager;
    
    public CommandPMWarning(SocialPointManager aSocialPointManager) {
        SocialPointManager = aSocialPointManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        if (aCommandSender instanceof Player) {
            String lWarnedPlayer = "";
            String lReason = "";
            int lIndex = 0;
            int lPoints = 0;
            Player lPlayer = (Player)aCommandSender;
            for (String lstr : aStrings) {
                switch (lIndex) {
                    case 0:
                        lWarnedPlayer = lstr;
                        if (!Framework.plugin.existsPlayer(lWarnedPlayer)) {
                            lPlayer.sendMessage(ChatColor.RED.toString() + "No valid warned Player!");                           
                            return true;
                        }
                        break;
                    case 1:
                        try {
                            lPoints = Integer.parseInt(lstr);
                        } catch (Exception ex) {
                            lPlayer.sendMessage(ChatColor.RED.toString() + "No valid warning count!");                           
                            return true;
                        }
                        break;
                    default:
                        if (lReason.length() == 0) {
                            lReason = lstr;
                        }
                        else {
                            lReason = lReason + " " + lstr;
                        }
                }
                lIndex++;
            }
            if (lReason.length() == 0) {
                lPlayer.sendMessage(ChatColor.RED.toString() + "No valid Reason!");                                                           
            }
            PlayerManager lPM = Framework.plugin.getPlayerManager();
            lPM.increaseSocialPoint(lPlayer.getName(),"warning",lPoints,lReason,lWarnedPlayer);
            lPlayer.sendMessage(ChatColor.GREEN.toString() + "Warning accorded...");
            if (lPoints > 0) {
                SocialPointManager.Plugin.getServer().broadcastMessage(ChatColor.RED.toString() + "Warning for " + lWarnedPlayer + " pronounced. Reason: " + lReason);
            }
            else {
                SocialPointManager.Plugin.getServer().broadcastMessage(ChatColor.GREEN.toString() + "Warning for " + lWarnedPlayer + " pronounced. Reason: " + lReason);                
            }
        }
        return true;
    }
    
}
