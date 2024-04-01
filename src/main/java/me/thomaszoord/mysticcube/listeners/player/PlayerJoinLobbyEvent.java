package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.utils.packets.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerJoinLobbyEvent implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);

        TitleAPI.sendTitle(p, "§d§lMYSTIC CUBE","§7Welcome to the §5Prison!", 20, 20, 20);

        PrisonPlayer prisonPlayer = new PrisonPlayer(p.getPlayer(), p.getUniqueId());
        ScoreboardLobby.createScoreboard(prisonPlayer.getPlayer());

        SpawnCommand.teleportToSpawn(p);
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
