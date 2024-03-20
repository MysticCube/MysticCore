package me.thomaszoord.mysticcube.listeners.player;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
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

        User user = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId());

        if(user.getCachedData() == null) return;
        String prefix = user.getCachedData().getMetaData().getPrefix();

        if (prefix == null) {
            prefix = "";
        }


        String name = prefix  + user.getPrimaryGroup() + "§7 " + p.getName() + ": ";

        for(Player r : Bukkit.getOnlinePlayers()){
           r.sendMessage(name + message);
        }
    }


}
