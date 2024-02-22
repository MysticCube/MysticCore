package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockEvent implements Listener {

    @EventHandler
    public void onBreakBlockEvent(BlockBreakEvent e){
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(e.getPlayer());

        prisonPlayer.setBlocks(prisonPlayer.getBlocks() + 1);
    }
}
