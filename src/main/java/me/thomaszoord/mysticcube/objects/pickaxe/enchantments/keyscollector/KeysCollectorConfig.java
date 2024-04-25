package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.keyscollector;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.utils.config.ConfigAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.Arrays;

public class KeysCollectorConfig {

    public static String name = "Keys Collector";
    public static String color = "&7";

    // Info
    public static String guiItem = "TRIPWIRE_HOOK";
    public static String[] description = {"&7Increase your chance of", "&7obtaining gems while mining."};

    // Economy
    public static int price = 1000;
    public static int minimumLevel = 5;
    public static int maxLevel = 10000;
    public static int multiplierPrice = 20;

    // Abilities
    public static double baseActivationPercentage = 50.0;

    public static void loadDefaultSettings(String enchantmentName){

        ConfigAPI enchantmentConfig = new ConfigAPI(new File(Core.getPlugin().getDataFolder() + File.separator + "enchantments", enchantmentName + ".yml"));
        ConfigurationSection fortune = enchantmentConfig.getYamlConfiguration().getConfigurationSection(enchantmentName);

        if(fortune == null){
            fortune = enchantmentConfig.getYamlConfiguration().createSection(enchantmentName);

            ConfigurationSection infos = fortune.createSection("Info");
            ConfigurationSection economy = fortune.createSection("Economy");
            ConfigurationSection enchantment = fortune.createSection("Abilities");
            String translatedColor = translateColor(color);

            infos.set("Name", name);
            infos.set("Color", translatedColor);
            infos.set("GUItem", guiItem);
            infos.set("Description", Arrays.asList(translatedColors(description)));

            economy.set("Price", price);
            economy.set("MinimumLevel", minimumLevel);
            economy.set("MaxLevel", maxLevel);
            economy.set("MultiplierPrice", multiplierPrice);

            enchantment.set("BaseActivationPercentage", baseActivationPercentage);

            enchantmentConfig.saveDocument();

        }

        ConfigurationSection infos = fortune.getConfigurationSection("Info");
        ConfigurationSection economy = fortune.getConfigurationSection("Economy");
        ConfigurationSection enchantment = fortune.getConfigurationSection("Abilities");

        //ENCHANTMENT INFO
        name = infos.getString("Name");
        color = infos.getString("Color");
        guiItem = infos.getString("GUItem");
        description = infos.getStringList("Description").toArray(new String[0]);

        //ECONOMY
        price = economy.getInt("Price");
        minimumLevel = economy.getInt("MinimumLevel");
        maxLevel = economy.getInt("MaxLevel");
        multiplierPrice = economy.getInt("MultiplierPrice");

        //ENCHANTMENT ABILITIES
        baseActivationPercentage = enchantment.getDouble("BaseActivationPercentage");
    }


    private static String[] translatedColors(String[] textArray) {
        String[] translatedArray = new String[textArray.length];
        for (int i = 0; i < textArray.length; i++) {
            translatedArray[i] = translateColor(textArray[i]);
        }
        return translatedArray;
    }

    private static String translateColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }



}
