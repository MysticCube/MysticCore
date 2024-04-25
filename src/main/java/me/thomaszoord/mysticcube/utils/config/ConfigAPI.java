package me.thomaszoord.mysticcube.utils.config;

import me.thomaszoord.mysticcube.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigAPI {

    private File fileConfig;
    private FileConfiguration yamlConfiguration;

    public ConfigAPI(File configFile)
    {
        this.fileConfig = configFile;

        File dataFolder = Core.getPlugin().getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        if(!this.fileConfig.exists())
        {
            try
            {
                this.fileConfig.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        this.yamlConfiguration = YamlConfiguration.loadConfiguration(fileConfig);
    }


    public void saveDocument(){
        try {
            yamlConfiguration.save(fileConfig);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + fileConfig.getName() + " §awas sucessful saved '");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cError '" + fileConfig.getName() + "': was not sucessful saved!" + e.getMessage());
        }
    }

    public void saveLocation(String name, Location location){
        yamlConfiguration.set("locations." + name, location);

        saveDocument();
        Bukkit.getConsoleSender().sendMessage("§aThe location '" + name + "' was sucessfull saved!");
    }


    public Location getLocation(String location) {
        Object serializedLocation = yamlConfiguration.get("locations." + location);

        if(serializedLocation == null){
            Bukkit.getConsoleSender().sendMessage("§cThe location §e" + location + " §cwas not found.");
                return null;
        }


        if (serializedLocation instanceof Location) {
            return (Location) serializedLocation;
        }
        return null;
    }

    public FileConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }


}
