package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticDBRecord;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Iterator;

public class CommandPMPSList implements CommandExecutor {

    public PlayerStatisticManager PlayerStatisticManager;

    public CommandPMPSList(PlayerStatisticManager aPlayerStatisticManager) {
        PlayerStatisticManager = aPlayerStatisticManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        if (aStrings.length == 0) {
            Iterator<PlayerStatisticDBRecord> itr = PlayerStatisticManager.getPlayers();
            while (itr.hasNext()) {
                PlayerStatisticDBRecord rec = itr.next();
                aCommandSender.sendMessage(String.format("%s%s %s (%s)", ChatColor.GREEN.toString(), rec.Player, rec.getLastLoginAsString(), rec.getLastDurationAsString()));
            }
        } else {
            String playername = "";
            for (int i = 0; i < aStrings.length; i++) {
                if (playername.length() == 0)
                    playername = aStrings[i];
                else
                    playername = String.format("%s %s", playername, aStrings[i]);
            }
            PlayerStatisticDBRecord rec = PlayerStatisticManager.getPlayer(playername);
            if (rec != null) {
                aCommandSender.sendMessage(String.format("%s%s %s (%s)", ChatColor.DARK_GREEN.toString(), rec.Player, rec.getFirstLoginAsString(), rec.getTotalDurationAsString()));
                aCommandSender.sendMessage(String.format("%s%s %s (%s)", ChatColor.GREEN.toString(), rec.Player, rec.getLastLoginAsString(), rec.getLastDurationAsString()));
            } else {
                aCommandSender.sendMessage(ChatColor.RED.toString() + "No valid Player!");
            }
        }
        return true;
    }

}
