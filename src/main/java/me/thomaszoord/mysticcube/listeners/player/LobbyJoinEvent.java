package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class LobbyJoinEvent implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.sendTitle("§d§lMYSTIC CUBE", "§7Welcome to the §5Prison!");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);

        PrisonPlayer prisonPlayer = new PrisonPlayer(p.getPlayer(), p.getUniqueId());
        ScoreboardLobby.createScoreboard(prisonPlayer.getPlayer());
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
