package me.thomaszoord.mysticcube;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.thomaszoord.mysticcube.commands.admin.AdminCommand;
import me.thomaszoord.mysticcube.commands.player.MineCommand;
import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.listeners.packets.BlockInteractPacket;
import me.thomaszoord.mysticcube.listeners.player.PlayerLobbyBreakBlockEvent;
import me.thomaszoord.mysticcube.listeners.player.PlayerChatEvent;
import me.thomaszoord.mysticcube.listeners.world.LobbyWorldEvent;
import me.thomaszoord.mysticcube.listeners.player.PlayerJoinLobbyEvent;
import me.thomaszoord.mysticcube.mine.BlocksMapping;
import me.thomaszoord.mysticcube.mine.MineLevel;
import me.thomaszoord.mysticcube.utils.Configs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Core extends JavaPlugin {

    private static Core plugin;
    private static ProtocolManager protocolManager;

    public static Configs configs;

    public static List<MineLevel> mineLevels;





    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§aMysticCore | Plugin Enabled");
        Bukkit.getConsoleSender().sendMessage("§aPlugin developed by thomaszoord");
        Bukkit.getConsoleSender().sendMessage("");

        protocolManager = ProtocolLibrary.getProtocolManager();
        configs = new Configs();


        registerEvents();
        mineLevels = BlocksMapping.getBlocksList();


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
        this.getServer().getPluginManager().registerEvents(new PlayerJoinLobbyEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new NPCInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLobbyBreakBlockEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerBlockBreakEvent(), getPlugin());
        ProtocolLibrary.getProtocolManager().addPacketListener(new BlockInteractPacket(this));
        ProtocolLibrary.getProtocolManager().addPacketListener(new BlockBreakPacket(this));

        this.getCommand("mine").setExecutor(new MineCommand());


    }




    public static Core getPlugin() {
        return plugin;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
