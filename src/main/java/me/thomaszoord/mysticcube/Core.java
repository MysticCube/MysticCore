package me.thomaszoord.mysticcube;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.thomaszoord.mysticcube.commands.admin.AdminCommand;
import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import me.thomaszoord.mysticcube.listeners.player.LobbyBreakBlockEvent;
import me.thomaszoord.mysticcube.listeners.player.PlayerChatEvent;
import me.thomaszoord.mysticcube.listeners.world.LobbyWorldEvent;
import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import me.thomaszoord.mysticcube.utils.Configs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core plugin;
    private static ProtocolManager protocolManager;



    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§aMysticCore | Plugin Enabled");
        Bukkit.getConsoleSender().sendMessage("§aPlugin developed by thomaszoord");
        Bukkit.getConsoleSender().sendMessage("");

        protocolManager = ProtocolLibrary.getProtocolManager();
        registerEvents();
        loadLocations();


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

        this.getCommand("spawn").setExecutor(new SpawnCommand());



        this.getServer().getPluginManager().registerEvents(new LobbyWorldEvent(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyJoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new LobbyBreakBlockEvent(), this);
    }


    public void loadLocations(){
        SpawnCommand.spawnLocation = Configs.core.getLocation("Spawn");
    }



    public static Core getPlugin() {
        return plugin;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
