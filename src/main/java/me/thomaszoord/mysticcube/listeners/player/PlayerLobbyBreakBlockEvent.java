package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerLobbyBreakBlockEvent implements Listener {

    @EventHandler
    public void onBreakBlockEvent(BlockBreakEvent e){
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(e.getPlayer());

        if(prisonPlayer.getMine().getLocationHandleMineBlock().containsKey(e.getBlock().getLocation())){
            e.setCancelled(true);
            return;
        }
    }


}
