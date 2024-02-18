package me.thomaszoord.mysticcube;

import me.thomaszoord.mysticcube.listeners.MineEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticCube extends JavaPlugin {

    private MysticCube plugin;


    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§aMysticCube | Plugin Enabled");
        Bukkit.getConsoleSender().sendMessage("§aPlugin developed by thomaszoord");
        Bukkit.getConsoleSender().sendMessage("");

        registerEvents();
    }

    @Override
    public void onDisable() {


        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§cMysticCube | Plugin Unabled");
        Bukkit.getConsoleSender().sendMessage("§cPlugin developed by thomaszoord");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new MineEvent(), this);
    }

}
