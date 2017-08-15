/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.Framework;
import com.mahn42.framework.PlayerManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class CommandPMSPList implements CommandExecutor{

    public SocialPointManager SocialPointManager;
    
    public CommandPMSPList(SocialPointManager aSocialPointManager) {
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
            lIndex++;
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
                for (Player lOnPlayer : SocialPointManager.Plugin.getServer().getOnlinePlayers()) {
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
            List<PlayerManager.SocialPoint> lSPList = lPM.getSocialPoints(lPlayerName);
            aCommandSender.sendMessage(ChatColor.BLUE.toString() + "Social Points of " + lPlayerName);
            for (Iterator<PlayerManager.SocialPoint> it = lSPList.iterator(); it.hasNext();) {
                PlayerManager.SocialPoint lSP = it.next();
                String lstr = lSP.getName() + "=" + new Integer(lSP.getAmount()).toString();
                aCommandSender.sendMessage(ChatColor.GRAY.toString() + lstr);
            }
        }
        return true;
    }
    
}
