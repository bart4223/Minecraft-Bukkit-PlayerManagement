/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.framework.Framework;
import com.mahn42.framework.PlayerManager;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
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
        if (aCommandSender instanceof Player) {
            int lIndex = 0;
            String lPlayerName = "";
            Player lPlayer = (Player)aCommandSender;
            for (String lstr : aStrings) {
                switch (lIndex) {
                    case 0:
                        lPlayerName = lstr;
                        if (!Framework.plugin.existsPlayer(lPlayerName)) {
                            lPlayer.sendMessage(ChatColor.RED.toString() + "No valid Player!");                           
                            return true;
                        }
                        break;
                }
                lIndex++;
            }
            PlayerManager lPM = Framework.plugin.getPlayerManager();
            List<PlayerManager.SocialPoint> lSPList = lPM.getSocialPoints(lPlayerName);
            lPlayer.sendMessage(ChatColor.BLUE.toString() + "Social Points of " + lPlayerName);
            for (Iterator<PlayerManager.SocialPoint> it = lSPList.iterator(); it.hasNext();) {
                PlayerManager.SocialPoint lSP = it.next();
                String lstr = lSP.getName() + "=" + new Integer(lSP.getAmount()).toString();
                lPlayer.sendMessage(ChatColor.GRAY.toString() + lstr);
            }            
        }
        return true;
    }
    
}
