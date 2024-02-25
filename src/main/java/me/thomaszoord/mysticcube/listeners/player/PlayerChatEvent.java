package me.thomaszoord.mysticcube.listeners.player;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void chatASyncEvent(AsyncPlayerChatEvent e){
        e.setCancelled(true);

        Player p = e.getPlayer();
        String message = e.getMessage();

        String prefix = "§7" + p.getName() + ": ";
        p.sendMessage(prefix + message);

        for(Player r : p.getWorld().getPlayers()){
            if(r != p && r.getLocation().distance(p.getLocation()) < 100){
                //send message
                r.sendMessage(prefix + message);
            }
        }
    }


}
