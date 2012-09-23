/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

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

    public SocialPointManager SocialPointManager;
    
    public CommandPMWarningList(SocialPointManager aSocialPointManager) {
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
                lIndex++;
            }
            PlayerManager lPM = Framework.plugin.getPlayerManager();
            List<SocialPointHistory> lSPHList = lPM.getSocialPointHistory(lWarnedPlayer, "warning");
            lPlayer.sendMessage(ChatColor.BLUE.toString() + "Warning History of " + lWarnedPlayer);
            SimpleDateFormat lSdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            lIndex = 0;
            int lCount = lSPHList.size() - SocialPointManager.MaxShownSPListItems;
            for (Iterator<SocialPointHistory> it = lSPHList.iterator(); it.hasNext();) {
                SocialPointHistory lSPH = it.next();
                if (lIndex >= lCount ) {
                    String lstr = "Points=" + new Integer(lSPH.getAmount()).toString();
                    lPlayer.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Reason=" + lSPH.getReason();
                    lPlayer.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Distributor=" + lSPH.getChargePlayerName();
                    lPlayer.sendMessage(ChatColor.GRAY.toString() + lstr);
                    lstr = "Timestamp=" + lSdf.format(new Date(lSPH.getTimestamp()));
                    lPlayer.sendMessage(ChatColor.GRAY.toString() + lstr);
                    if (lIndex < lSPHList.size() - 1 ) {
                        lPlayer.sendMessage(ChatColor.DARK_GRAY.toString() + "====================================================");
                    }
                }
                lIndex++;
            }           
        }
        return true;
    }
    
}
