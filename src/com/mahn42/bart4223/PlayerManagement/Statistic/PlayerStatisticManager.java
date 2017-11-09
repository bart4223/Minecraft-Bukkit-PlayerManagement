package com.mahn42.bart4223.PlayerManagement.Statistic;

import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMPSList;
import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMPSTop;
import com.mahn42.bart4223.PlayerManagement.PlayerManagement;
import com.mahn42.framework.Framework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

public class PlayerStatisticManager implements Listener {

    protected Logger FLog;
    protected PlayerManagement FOwner;
    protected PlayerStatisticDBSet FDBPlayerStatistic = null;

    protected void InitPSDB() {
        if (FDBPlayerStatistic == null) {
            File lFolder = FOwner.getDataFolder();
            if (!lFolder.exists()) {
                lFolder.mkdirs();
            }
            String lPath = lFolder.getPath();
            lPath = lPath + File.separatorChar + "playerstatistic.csv";
            File lFile = new File(lPath);
            FDBPlayerStatistic = new PlayerStatisticDBSet(lFile);
            FDBPlayerStatistic.load();
            writeInfo(String.format("Datafile %s loaded. (Record count: %d)", lFile.toString() , FDBPlayerStatistic.size()));
        }
    }

    protected PlayerStatisticDBRecord newPlayer(String aPlayer) {
        return FDBPlayerStatistic.newRecord(aPlayer);
    }

    protected void PlayerLogin(String aPlayer) {
        PlayerStatisticDBRecord rec = FDBPlayerStatistic.getRecord(aPlayer);
        if (rec == null) {
            rec = newPlayer(aPlayer);
        }
        rec.CurrentLogin = new Date();
        writeInfo(String.format("Player %s logged in.", aPlayer));
    }

    protected void PlayerLogoff(String aPlayer) {
        PlayerStatisticDBRecord rec = FDBPlayerStatistic.getRecord(aPlayer);
        Date now = new Date();
        Integer duration = (int)((now.getTime() - rec.CurrentLogin.getTime()) / 1000);
        rec.LastDuration = duration;
        rec.TotalDuration = rec.TotalDuration + duration;
        rec.LastLogin = rec.CurrentLogin;
        rec.CurrentLogin = new Date(0);
        writeInfo(String.format("Player %s logged off.", aPlayer));
    }

    protected void writeInfo(String aInfo) {
        FLog.info(aInfo);
    }

    public PlayerStatisticManager(PlayerManagement aOwner) {
        FOwner = aOwner;
        FLog = FOwner.getLogger();
    }

    public void Initialize() {
        InitPSDB();
        Framework.plugin.registerSaver(FDBPlayerStatistic);
        FOwner.getServer().getPluginManager().registerEvents(this, FOwner);
        FOwner.getCommand("pm_pslist").setExecutor(new CommandPMPSList(this));
        FOwner.getCommand("pm_pstop").setExecutor(new CommandPMPSTop(this));
    }

    @EventHandler
    public void playerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerLogin(player.getDisplayName());
    }

    @EventHandler
    public void playerLogoff(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerLogoff(player.getDisplayName());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player  = event.getPlayer();
        PlayerStatisticDBRecord rec = getPlayer(player);
        if (rec != null) {
            rec.AddBlockBreak();
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player  = event.getPlayer();
        PlayerStatisticDBRecord rec = getPlayer(player);
        if (rec != null) {
            rec.AddBlockPlaced();
        }
    }

    public Iterator<PlayerStatisticDBRecord> getPlayers() {
        return FDBPlayerStatistic.iterator();
    }

    public PlayerStatisticDBRecord getPlayer(Player aPlayer) {
        return getPlayer(aPlayer.getName());
    }

    public PlayerStatisticDBRecord getPlayer(String aPlayer) {
        Iterator<PlayerStatisticDBRecord> itr = getPlayers();
        while (itr.hasNext()) {
            PlayerStatisticDBRecord player = itr.next();
            if (player.Player.equalsIgnoreCase(aPlayer)) {
                return player;
            }
        }
        return null;
    }

    public PlayerStatisticDBRecord getTopPlayer() {
        PlayerStatisticDBRecord res = null;
        Iterator<PlayerStatisticDBRecord> itr = getPlayers();
        while (itr.hasNext()) {
            PlayerStatisticDBRecord player = itr.next();
            if (res == null || res.TotalDuration < player.TotalDuration) {
                res = player;
            }

        }
        return res;
    }

}
