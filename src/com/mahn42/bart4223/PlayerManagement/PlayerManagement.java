/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Nils
 */
public class PlayerManagement extends JavaPlugin {
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
    }

    public SocialPointManager SocialPointManager;
    
    @Override
    public void onEnable() {
      fLog = this.getLogger();
      SocialPointManager = new SocialPointManager(this);
      readPlayerManagementConfig();
      getCommand("pm_splist").setExecutor(new CommandPMSPList(SocialPointManager));
      getCommand("pm_warning").setExecutor(new CommandPMWarning(SocialPointManager));
      getCommand("pm_warninglist").setExecutor(new CommandPMWarningList(SocialPointManager));
      getCommand("pm_warningpoints").setExecutor(new CommandPMWarningPoints(SocialPointManager));
    }
       
    @Override
    public void onDisable() { 

    }
       
    protected Logger fLog;

    protected void readPlayerManagementConfig() {
        FileConfiguration lConfig = getConfig();
        SocialPointManager.MaxShownSPListItems = lConfig.getInt("MaxShownSPListItems");
        SocialPointManager.MaxSPHistoryItems = lConfig.getInt("MaxSPHistoryItems");
    }
        
}
