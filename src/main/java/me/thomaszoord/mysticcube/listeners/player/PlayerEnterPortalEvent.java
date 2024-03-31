package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerEnterPortalEvent implements Listener {

    @EventHandler
    public void portalEnterEvent(PlayerPortalEvent e){
        Player p = e.getPlayer();
        e.setCancelled(true);

        SpawnCommand.teleportToSpawn(p);
    }
}
