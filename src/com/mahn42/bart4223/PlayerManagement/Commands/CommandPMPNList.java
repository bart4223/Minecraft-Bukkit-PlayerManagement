package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.News.PlayerNewsManager;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticDBRecord;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPMPNList implements CommandExecutor {

    public com.mahn42.bart4223.PlayerManagement.News.PlayerNewsManager PlayerNewsManager;

    public CommandPMPNList(PlayerNewsManager aPlayerNewsManager) {
        PlayerNewsManager = aPlayerNewsManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        PlayerStatisticDBRecord player = PlayerNewsManager.getOwner().PlayerStatisticManager.getPlayer(aCommandSender.getName());
        if (player != null) {
            aCommandSender.sendMessage(String.format("%sNews since %s\n%s", ChatColor.GREEN.toString(), player.getLastLoginAsString(), PlayerNewsManager.getPlayerNews(aCommandSender.getName())));
        }
        return true;
    }

}
