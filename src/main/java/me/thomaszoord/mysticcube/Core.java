package me.thomaszoord.mysticcube;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.thomaszoord.mysticcube.commands.admin.AdminCommand;
import me.thomaszoord.mysticcube.commands.mine.MineCommand;
import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.listeners.packets.BlockInteractPacket;

import me.thomaszoord.mysticcube.listeners.player.*;
import me.thomaszoord.mysticcube.listeners.world.LobbyWorldEvent;
import me.thomaszoord.mysticcube.objects.mine.mineblock.BlocksMapping;
import me.thomaszoord.mysticcube.objects.mine.mineblock.MineLevel;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.emeraldmine.EmeraldMineConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.fortune.FortuneConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.jackhammer.JackhammerConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.keyscollector.KeysCollectorConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.laser.LaserConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.nuker.NukerConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.pointbuster.PointBusterConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.tokencollector.TokenCollectorConfig;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.velocity.VelocityConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Core extends JavaPlugin {

    private static Core plugin;
    private static ProtocolManager protocolManager;
    public static List<MineLevel> mineLevels;


    @Override
    public void onEnable() {
        plugin = this;

        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§aMysticCore | Plugin Enabled");
        Bukkit.getConsoleSender().sendMessage("§aPlugin developed by thomaszoord");
        Bukkit.getConsoleSender().sendMessage("");

        protocolManager = ProtocolLibrary.getProtocolManager();

        FortuneConfig.loadDefaultSettings("Fortune");
        PointBusterConfig.loadDefaultSettings("PointBuster");
        TokenCollectorConfig.loadDefaultSettings("TokenCollector");
        VelocityConfig.loadDefaultSettings("Velocity");
        KeysCollectorConfig.loadDefaultSettings("KeysCollector");
        EmeraldMineConfig.loadDefaultSettings("EmeraldMine");
        LaserConfig.loadDefaultSettings("Laser");
        JackhammerConfig.loadDefaultSettings("Jackhammer");
        NukerConfig.loadDefaultSettings("Nuker");

        registerEvents();
        mineLevels = BlocksMapping.getBlocksList();


    }

    @Override
    public void onDisable() {


        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§cMysticCube | Plugin Disabled");
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
        this.getServer().getPluginManager().registerEvents(new PlayerEnterPortalEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractItemEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInventoryClickEvent(), this);
        ProtocolLibrary.getProtocolManager().addPacketListener(new BlockInteractPacket(this));
        ProtocolLibrary.getProtocolManager().addPacketListener(new BlockBreakPacket(this));
//        ProtocolLibrary.getProtocolManager().addPacketListener(new PlayerTabListPlayerInfo(this));

        this.getCommand("mine").setExecutor(new MineCommand());


    }




    public static Core getPlugin() {
        return plugin;
    }

    public static ProtocolManager getProtocolManager() {
        return protocolManager;
    }



}
