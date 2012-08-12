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
public class CommandPMWarningPoints implements CommandExecutor{

    public SocialPointManager SocialPointManager;
    
    public CommandPMWarningPoints(SocialPointManager aSocialPointManager) {
        SocialPointManager = aSocialPointManager;
    }
    
    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        if (aCommandSender instanceof Player) {
            int lIndex = 0;
            String lWarnedPlayer = "";
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
                }
            }
            PlayerManager lPM = Framework.plugin.getPlayerManager();
            PlayerManager.SocialPoint lSP = lPM.getSocialPoint(lWarnedPlayer, "warning");
            if (lSP != null) {
                lPlayer.sendMessage(ChatColor.BLUE.toString() + "Warning Points of " + lWarnedPlayer);
                lPlayer.sendMessage(ChatColor.GRAY.toString() + "Points=" + new Integer(lSP.getAmount()).toString());
            }
                
        }
        return true;
    }
    
}
