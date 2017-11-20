/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahn42.bart4223.PlayerManagement;

import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMSPList;
import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMWarning;
import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMWarningList;
import com.mahn42.bart4223.PlayerManagement.Commands.CommandPMWarningPoints;
import com.mahn42.bart4223.PlayerManagement.News.PlayerNewsManager;
import com.mahn42.bart4223.PlayerManagement.SocialPoint.SocialPointListenerWarning;
import com.mahn42.bart4223.PlayerManagement.SocialPoint.SocialPointManager;
import com.mahn42.bart4223.PlayerManagement.SocialPoint.SocialPointType;
import com.mahn42.bart4223.PlayerManagement.Statistic.PlayerStatisticManager;
import com.mahn42.framework.Framework;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
    public PlayerStatisticManager PlayerStatisticManager;
    public PlayerNewsManager PlayerNewsManager;
    
    @Override
    public void onEnable() {
      fLog = this.getLogger();
      PlayerStatisticManager = new PlayerStatisticManager(this);
      PlayerStatisticManager.Initialize();
      PlayerNewsManager = new PlayerNewsManager(this);
      PlayerNewsManager.Initialize();
      SocialPointManager = new SocialPointManager(this);
      Framework.plugin.registerPlayerManager(SocialPointManager);
      readPlayerManagementConfig();
      readSocialPointTypesConfig();
      getCommand("pm_splist").setExecutor(new CommandPMSPList(SocialPointManager));
      getCommand("pm_warning").setExecutor(new CommandPMWarning(SocialPointManager));
      getCommand("pm_warninglist").setExecutor(new CommandPMWarningList(SocialPointManager));
      getCommand("pm_warningpoints").setExecutor(new CommandPMWarningPoints(SocialPointManager));
      getServer().getPluginManager().registerEvents(new SocialPointListenerWarning(SocialPointManager), this);
    }
       
    @Override
    public void onDisable() { 

    }
       
    protected Logger fLog;
    protected FileConfiguration fSocialPointTypesConfig;
    protected File fSocialPointTypesConfigFile;

    protected void readPlayerManagementConfig() {
        FileConfiguration lConfig = getConfig();
        SocialPointManager.MaxShownSPListItems = lConfig.getInt("MaxShownSPListItems");
        SocialPointManager.MaxSPHistoryItems = lConfig.getInt("MaxSPHistoryItems");
    }

    protected void LoadSocialPointTypesConfig() {
        if (fSocialPointTypesConfigFile == null) {
            fSocialPointTypesConfigFile = new File(getDataFolder(), "socialpointtypes.yml");
        }
        fSocialPointTypesConfig = YamlConfiguration.loadConfiguration(fSocialPointTypesConfigFile);
    }
    
    protected FileConfiguration getSocialPointTypesConfig() {
        if (fSocialPointTypesConfig == null) {
            LoadSocialPointTypesConfig();
        }
        return fSocialPointTypesConfig;
    }

    protected void readSocialPointTypesConfig() {
        FileConfiguration lConfig = getSocialPointTypesConfig();
        if (lConfig != null) {
            fLog.info("SocialPointTypes config loaded...");
        }
        List<Object> lTypes = (List<Object>)lConfig.getList("Types");
        if (lTypes != null) {
            Iterator<Object> iter = lTypes.iterator();
            while (iter.hasNext()) {
                LinkedHashMap lType = (LinkedHashMap)iter.next();
                SocialPointType lSPType = new SocialPointType(lType.get("Name").toString());
                List<Object> lProps = (List<Object>)lType.get("Props");
                Iterator<Object> literSPTP = lProps.iterator();
                while (literSPTP.hasNext()) {
                    String lProp = (String)literSPTP.next();
                    String[] lTemp = lProp.split("=");
                    Object lPropValue;
                    try {
                        lPropValue = Integer.parseInt(lTemp[1]);
                    } catch (Exception ex) {
                        lPropValue = lTemp[1];
                    }                    
                    lSPType.AddProp(lTemp[0],lPropValue); 
                }    
                SocialPointManager.AddSocialPoinType(lSPType);
                fLog.info("SocialPointType " + lSPType.Name + " established with " + new Integer(lSPType.GetPropsCount()).toString() + " Prop(s)" );
            }            
        }
    }
        
}
