package com.mahn42.bart4223.PlayerManagement.Commands;

import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticDBRecord;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class CommandPMPSList implements CommandExecutor {

    public PlayerStatisticManager PlayerStatisticManager;

    public CommandPMPSList(PlayerStatisticManager aPlayerStatisticManager) {
        PlayerStatisticManager = aPlayerStatisticManager;
    }

    @Override
    public boolean onCommand(CommandSender aCommandSender, Command aCommand, String aString, String[] aStrings) {
        if (aStrings.length == 0) {
            ArrayList<PlayerStatisticDBRecord> players = new ArrayList<PlayerStatisticDBRecord>();
            Iterator<PlayerStatisticDBRecord> itr = PlayerStatisticManager.getPlayers();
            while (itr.hasNext()) {
                players.add(itr.next());
            }
            Collections.sort(players, new Comparator<PlayerStatisticDBRecord>(){
                @Override
                public int compare(PlayerStatisticDBRecord p1, PlayerStatisticDBRecord p2) {
                    return p1.Player.toUpperCase().compareTo(p2.Player.toUpperCase());
                }
            });
            for (PlayerStatisticDBRecord player : players) {
                aCommandSender.sendMessage(String.format("%s%s %s (%s)", ChatColor.GREEN.toString(), player.Player, player.getLastLoginAsString(), player.getLastDurationAsString()));
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
                aCommandSender.sendMessage(String.format("%s%s Blocks break %d", ChatColor.GREEN.toString(), rec.Player, rec.BlocksBreak));
                aCommandSender.sendMessage(String.format("%s%s Blocks placed %d", ChatColor.GREEN.toString(), rec.Player, rec.BlocksPlaced));
                aCommandSender.sendMessage(String.format("%s%s Kills Player %d", ChatColor.RED.toString(), rec.Player, rec.KillsPlayer));
                aCommandSender.sendMessage(String.format("%s%s Kills NPC %d", ChatColor.YELLOW.toString(), rec.Player, rec.KillsNPC));
                if (aCommandSender.isOp()) {
                    aCommandSender.sendMessage(String.format("%s%s Last Position (x y z) %s", ChatColor.BLUE.toString(), rec.Player, rec.getLastPositionAsString()));
                }
            } else {
                aCommandSender.sendMessage(ChatColor.RED.toString() + "No valid Player!");
            }
        }
        return true;
    }

}
