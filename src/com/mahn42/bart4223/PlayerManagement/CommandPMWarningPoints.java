/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.Framework;
import com.mahn42.framework.PlayerManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Nils
 */
public class CommandPMWarningPoints implements CommandExecutor{

    public SocialPointManager SocialPointManager;
    
    public CommandPMWarningPoints(SocialPointManager aSocialPointManager) {
        SocialPointManager = aSocialPointManager;
    }
    
    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        int lIndex = 0;
        ArrayList<String> lPlayerNames;
        lPlayerNames = new ArrayList<String>();
        String lPlayerName = "";
        for (String lstr : aStrings) {
            switch (lIndex) {
                case 0:
                    lPlayerName = lstr;
                    if (!Framework.plugin.existsPlayer(lPlayerName) && !lPlayerName.equalsIgnoreCase("all")) {
                        aCommandSender.sendMessage(ChatColor.RED.toString() + "No valid Player!");                           
                        return true;
                    }
                    break;
            }
        }
        if (lPlayerName.length() == 0 || lPlayerName.equalsIgnoreCase("all")) {
            if (aCommandSender instanceof Player && lPlayerName.length() == 0) {
                lPlayerName = aCommandSender.getName();
                lPlayerNames.add(lPlayerName);
            }
            else {
                OfflinePlayer[] offPlayers = SocialPointManager.Plugin.getServer().getOfflinePlayers();
                for (OfflinePlayer lOffPlayer : offPlayers) {
                    lPlayerNames.add(lOffPlayer.getName());
                }
                Player[] onPlayers = SocialPointManager.Plugin.getServer().getOnlinePlayers();
                for (Player lOnPlayer : onPlayers) {
                    lPlayerNames.add(lOnPlayer.getName());
                }
            }
        }
        else {
            lPlayerNames.add(lPlayerName);
        }
        PlayerManager lPM = Framework.plugin.getPlayerManager();
        for (Iterator<String> itPN = lPlayerNames.iterator(); itPN.hasNext();) {
            lPlayerName = itPN.next();
            PlayerManager.SocialPoint lSP = lPM.getSocialPoint(lPlayerName, "warning");
            if (lSP != null) {
                aCommandSender.sendMessage(ChatColor.BLUE.toString() + "Warning Points of " + lPlayerName);
                aCommandSender.sendMessage(ChatColor.GRAY.toString() + "Points=" + new Integer(lSP.getAmount()).toString());
            }
        }
        return true;
    }
    
}
