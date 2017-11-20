package com.mahn42.bart4223.PlayerManagement.News;

import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMPNAdd;
import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMPNList;
import com.mahn42.bart4223.PlayerManagement.PlayerManagement;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticDBRecord;
import com.mahn42.framework.Framework;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.Iterator;
import java.util.logging.Logger;

public class PlayerNewsManager implements Listener {

    protected Logger FLog;
    protected PlayerManagement FOwner;
    protected PlayerNewsDBSet FDBPlayerNews;

    protected void InitPNDB() {
        if (FDBPlayerNews == null) {
            File lFolder = FOwner.getDataFolder();
            if (!lFolder.exists()) {
                lFolder.mkdirs();
            }
            String lPath = lFolder.getPath();
            lPath = lPath + File.separatorChar + "playernews.csv";
            File lFile = new File(lPath);
            FDBPlayerNews = new PlayerNewsDBSet(lFile);
            FDBPlayerNews.load();
            writeInfo(String.format("Datafile %s loaded. (Record count: %d)", lFile.toString() , FDBPlayerNews.size()));
        }
    }

    protected void writeInfo(String aInfo) {
        FLog.info(aInfo);
    }

    protected String PlayerLogin(String aPlayer) {
        return getPlayerNews(aPlayer);
    }

    public PlayerNewsManager(PlayerManagement aOwner) {
        FOwner = aOwner;
        FLog = FOwner.getLogger();
    }

    public PlayerManagement getOwner() {
        return FOwner;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void playerLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String news = PlayerLogin(player.getName());
        if (news.length() > 0) {
            news = String.format("%s***%s-News***\n%s", ChatColor.GREEN.toString(), FOwner.getServer().getServerName(), news);
            event.setJoinMessage(String.format("%s\n%s", event.getJoinMessage(), news));
        }
    }

    public void Initialize() {
        InitPNDB();
        Framework.plugin.registerSaver(FDBPlayerNews);
        FOwner.getServer().getPluginManager().registerEvents(this, FOwner);
        FOwner.getCommand("pm_pnadd").setExecutor(new CommandPMPNAdd(this));
        FOwner.getCommand("pm_pnlist").setExecutor(new CommandPMPNList(this));
    }

    public PlayerNewsDBRecord publishNews(String aPlayer, String aNews, PlayerNewsDBRecord.eImportance aImportance) {
        return FDBPlayerNews.newRecord(aPlayer, aNews, aImportance);
    }

    public Iterator<PlayerNewsDBRecord> getNews() {
        return FDBPlayerNews.iterator();
    }

    public String getPlayerNews(String aPlayer) {
        String res = "";
        PlayerStatisticDBRecord player = FOwner.PlayerStatisticManager.getPlayer(aPlayer);
        if (player != null) {
            res = getPlayerNews(player);
        }
        return res;
    }

    public String getPlayerNews(PlayerStatisticDBRecord aPlayer) {
        String res = "";
        Iterator<PlayerNewsDBRecord> itr = FDBPlayerNews.iterator();
        while (itr.hasNext()) {
            PlayerNewsDBRecord n = itr.next();
            if (!n.Publisher.toUpperCase().equals(aPlayer.Player.toUpperCase()) && n.Released.after(aPlayer.LastLogin)) {
                String color = "";
                switch (n.getImportance()) {
                    case Normal:
                        color = ChatColor.WHITE.toString();
                        break;
                    case Important:
                        color = ChatColor.YELLOW.toString();
                        break;
                    case VeryImportant:
                        color = ChatColor.RED.toString();
                        break;
                }
                if (res.length() == 0) {
                    res = String.format("%s%s", color, n.getNews());
                }
                else {
                    res = String.format("%s\n%s%s", res, color, n.getNews());
                }
            }
        }
        return res;
    }

}
