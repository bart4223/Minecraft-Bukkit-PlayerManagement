package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticDBRecord;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPMPSTop implements CommandExecutor {

    public PlayerStatisticManager PlayerStatisticManager;

    public CommandPMPSTop(PlayerStatisticManager aPlayerStatisticManager) {
        PlayerStatisticManager = aPlayerStatisticManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        PlayerStatisticDBRecord rec = PlayerStatisticManager.getTopPlayer();
        if (rec != null) {
            aCommandSender.sendMessage(String.format("%sTop-Player %s", ChatColor.GOLD.toString(), rec.Player));
            aCommandSender.sendMessage(String.format("%s%s (%s)", ChatColor.GOLD.toString(), rec.getFirstLoginAsString(), rec.getTotalDurationAsString()));
            aCommandSender.sendMessage(String.format("%s%s (%s)", ChatColor.GOLD.toString(), rec.getLastLoginAsString(), rec.getLastDurationAsString()));
        } else {
            aCommandSender.sendMessage(ChatColor.RED.toString() + "No Top Player!");
        }
        return true;
    }

}
