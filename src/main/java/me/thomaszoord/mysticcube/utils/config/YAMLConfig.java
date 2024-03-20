package me.thomaszoord.mysticcube.utils.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class YAMLConfig extends YamlConfiguration{

    private final String fileName;
    private String path;
    private File file;

    public YAMLConfig(String fileName){
        this.fileName = fileName;
        this.file = new File(fileName + ".yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public YAMLConfig(String fileName, String path, Plugin plugin){
        this.fileName = fileName;
        this.path = path;
        this.file = new File(path + "." + fileName + ".yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void saveFile(){
        try{
            this.save(fileName);
            Bukkit.getConsoleSender().sendMessage("§aThis file ->" + fileName +" was successful saved!");

        } catch (IOException e){
            Bukkit.getConsoleSender().sendMessage("§cThis file ->" + fileName +" cannot be saved.");
        }
    }


    public void saveLocation(String location, Location l){

        ConfigurationSection section = createSection(path);
        section.set("location", l.serialize());

        this.set(location, section);

        saveFile();
    }
    public Location getLocation(String path) {
        ConfigurationSection section = getConfigurationSection(path);
        if (section != null && section.isConfigurationSection("location")) {
            ConfigurationSection locSection = section.getConfigurationSection("location");
            return Location.deserialize(locSection.getValues(true));
        }
        return null;
    }




}
