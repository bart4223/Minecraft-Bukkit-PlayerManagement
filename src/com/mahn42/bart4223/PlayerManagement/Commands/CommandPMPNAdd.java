package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.News.PlayerNewsDBRecord;
import com.mahn42.bart4223.PlayerManagement.News.PlayerNewsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPMPNAdd implements CommandExecutor {

    public PlayerNewsManager PlayerNewsManager;

    public CommandPMPNAdd(PlayerNewsManager aPlayerNewsManager) {
        PlayerNewsManager = aPlayerNewsManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        String news = "";
        Integer importance = 0;
        for (int i = 0; i < aStrings.length; i++) {
            Boolean lOK = (i == 0);
            if (lOK) {
                try {
                     importance = Integer.parseInt(aStrings[i]);
                } catch (Exception e) {
                    lOK = false;
                }
            }
            if (!lOK) {
                if (news.length() == 0)
                    news = aStrings[i];
                else
                    news = String.format("%s %s", news, aStrings[i]);
            }
        }
        PlayerNewsDBRecord.eImportance Importance = PlayerNewsDBRecord.eImportance.Normal;
        switch (importance) {
            case 0:
                Importance = PlayerNewsDBRecord.eImportance.Normal;
                break;
            case 1:
                Importance = PlayerNewsDBRecord.eImportance.Important;
                break;
            case 2:
                Importance = PlayerNewsDBRecord.eImportance.VeryImportant;
                break;
        }
        PlayerNewsManager.publishNews(aCommandSender.getName(), news, Importance);
        aCommandSender.sendMessage(String.format("%sNews added.", ChatColor.GREEN.toString()));
        return true;
    }

}
