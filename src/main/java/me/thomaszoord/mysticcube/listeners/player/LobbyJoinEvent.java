package me.thomaszoord.mysticcube.listeners.player;

import fr.mrmicky.fastboard.FastBoard;
import me.thomaszoord.mysticcube.listeners.scoreboard.Scoreboard;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.utils.colorapi.ColorAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LobbyJoinEvent implements Listener {

    BossBar bossBar;

    {
        bossBar = Bukkit.createBossBar(
                "§5Join our community: §fdiscord.gg/mysticcube"
                , BarColor.PURPLE,
                BarStyle.SOLID);
    }



    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.sendTitle(ColorAPI.colorizeRGB(ChatColor.BOLD + "{#ff00dd}MYSTIC CUBE"), "Welcome to the Prison!", 30, 80, 30);
        p.playSound(p, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

        PrisonPlayer prisonPlayer = new PrisonPlayer(p.getPlayer(), p.getUniqueId());

        Scoreboard.createScoreboard(p);
        bossBar.addPlayer(p);
                
                
    }


    public static void sendMessageConsole(String message){
        Bukkit.getConsoleSender().sendMessage("§d[MYSTIC CUBE] " + message);
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){

        Player p = e.getPlayer();
        e.setQuitMessage(null);

        PrisonPlayerManager.removePrisonPlayer(PrisonPlayerManager.getPrisonPlayer(p));
        bossBar.removePlayer(p);





    }
}
