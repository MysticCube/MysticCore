package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.gui.PickaxeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractItemEvent implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(p.getItemInHand().equals(prisonPlayer.getPickaxe().getPickaxeItemStack())){
                PickaxeGUI.openPickaxeGUI(p);
            }
        }


    }
}
