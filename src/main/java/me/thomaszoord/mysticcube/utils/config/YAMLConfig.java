package me.thomaszoord.mysticcube.utils.config;

import me.thomaszoord.mysticcube.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class YAMLConfig {

    private File fileConfig;
    private FileConfiguration yamlConfiguration;

    public YAMLConfig(File configFile)
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
            yamlConfiguration.save(fileConfig); // Salva a configuração no arquivo
            Bukkit.getConsoleSender().sendMessage(fileConfig.getName() + "§awas sucessful saved '");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cError '" + fileConfig.getName() + "': was not sucessful saved!" + e.getMessage());
        }
    }

    public void saveLocation(String name, Location location){
        yamlConfiguration.set("locations." + name, location);

        try {
            yamlConfiguration.save(fileConfig); // Salva a configuração no arquivo
            Bukkit.getConsoleSender().sendMessage("§aThe location '" + name + "' was sucessfull saved!");
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cError '" + name + "': was not sucessful saved!" + e.getMessage());
        }



    }


    public Location getLocation(String location) {
        Object serializedLocation = yamlConfiguration.get("locations." + location);
        if (serializedLocation instanceof Location) {
            return (Location) serializedLocation;
        }
        return null;
    }






}