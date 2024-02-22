package me.thomaszoord.mysticcube;

import me.thomaszoord.mysticcube.commands.AdminCommand;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import me.thomaszoord.mysticcube.listeners.player.PlayerBreakBlockEvent;
import me.thomaszoord.mysticcube.listeners.world.LobbyWorldEvent;
import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
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
        this.getCommand("admin").setExecutor(new AdminCommand());
        this.getCommand("admin").setTabCompleter(new AdminCommand());




        this.getServer().getPluginManager().registerEvents(new LobbyWorldEvent(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyJoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerBreakBlockEvent(), this);
    }

    public MysticCube getPlugin() {
        return plugin;
    }
}
