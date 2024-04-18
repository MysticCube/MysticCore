package me.thomaszoord.mysticcube.utils.config;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.utils.config.ConfigAPI;

import java.io.File;

public class Configs {
    public static ConfigAPI core = new ConfigAPI(new File(Core.getPlugin().getDataFolder() + "/configs/", "locations.yml"));
    public static ConfigAPI config = new ConfigAPI(new File(Core.getPlugin().getDataFolder()+ "/configs/", "mine.yml"));
    public static ConfigAPI language = new ConfigAPI(new File(Core.getPlugin().getDataFolder(), "language.yml"));
}
