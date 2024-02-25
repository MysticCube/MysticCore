package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.listeners.scoreboard.Scoreboard;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class LobbyJoinEvent implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.sendTitle("MYSTIC CUBE", "Welcome to the Prison!");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);

        PrisonPlayer prisonPlayer = new PrisonPlayer(p.getPlayer(), p.getUniqueId());
        Scoreboard.createScoreboard(prisonPlayer.getPlayer());
    }




    public static void sendMessageConsole(String message){
        Bukkit.getConsoleSender().sendMessage("§d[MYSTIC CUBE] " + message);
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){

        Player p = e.getPlayer();
        e.setQuitMessage(null);

        PrisonPlayerManager.removePrisonPlayer(PrisonPlayerManager.getPrisonPlayer(p));
    }
}
