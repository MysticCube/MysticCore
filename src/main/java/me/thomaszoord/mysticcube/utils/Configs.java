package me.thomaszoord.mysticcube.utils;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.utils.config.YAMLConfig;

import java.io.File;

public class Configs {

    public static YAMLConfig core = new YAMLConfig(new File(Core.getPlugin().getDataFolder(), "config.yml"));
    public static YAMLConfig language = new YAMLConfig(new File(Core.getPlugin().getDataFolder(), "language.yml"));
}
