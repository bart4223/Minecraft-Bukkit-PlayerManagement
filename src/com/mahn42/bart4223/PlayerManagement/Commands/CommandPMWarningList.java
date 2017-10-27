/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.SocialPoint.SocialPointManager;
import com.mahn42.framework.Framework;
import com.mahn42.framework.PlayerManager;
import com.mahn42.framework.PlayerManager.SocialPointHistory;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CommandPMWarningList implements CommandExecutor{

    public com.mahn42.bart4223.PlayerManagement.SocialPoint.SocialPointManager SocialPointManager;
    
    public CommandPMWarningList(SocialPointManager aSocialPointManager) {
        SocialPointManager = aSocialPointManager;
    }
    
    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        int lIndex = 0;
        String lPlayerName = "";
        for (String lstr : aStrings) {
            switch (lIndex) {
                case 0:
                    lPlayerName = lstr;
                    if (!Framework.plugin.existsPlayer(lPlayerName)) {
                        aCommandSender.sendMessage(ChatColor.RED.toString() + "No valid Player!");                           
                        return true;
                    }
                    break;
            }
            lIndex++;
        }
        PlayerManager lPM = Framework.plugin.getPlayerManager();
        if (lPlayerName.length() == 0 && aCommandSender instanceof Player) {
            lPlayerName = aCommandSender.getName();
        }
        if (lPlayerName.length() != 0) {
            List<SocialPointHistory> lSPHList = lPM.getSocialPointHistory(lPlayerName, "warning");
            aCommandSender.sendMessage(ChatColor.BLUE.toString() + "Warning History of " + lPlayerName);
            SimpleDateFormat lSdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            lIndex = 0;
            int lCount = lSPHList.size() - SocialPointManager.MaxShownSPListItems;
            for (Iterator<SocialPointHistory> it = lSPHList.iterator(); it.hasNext();) {
                SocialPointHistory lSPH = it.next();
                if (lIndex >= lCount ) {
                    String lstr = "Points=" + new Integer(lSPH.getAmount()).toString();
                    aCommandSender.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Reason=" + lSPH.getReason();
                    aCommandSender.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Distributor=" + lSPH.getChargePlayerName();
                    aCommandSender.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Timestamp=" + lSdf.format(new Date(lSPH.getTimestamp()));
                    aCommandSender.sendMessage(ChatColor.GRAY.toString() + lstr);
                    if (lIndex < lSPHList.size() - 1 ) {
                        aCommandSender.sendMessage(ChatColor.DARK_GRAY.toString() + "====================================================");
                    }
                }
                lIndex++;
            }           
        }
        return true;
    }
    
}
