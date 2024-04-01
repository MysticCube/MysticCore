package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerInventoryClickEvent implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);


        if(e.getCurrentItem().equals(prisonPlayer.getPickaxe().getPickaxeItemStack()) || e.getCurrentItem().getType().equals(Material.BOOK)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void inventoryDropEvent(PlayerDropItemEvent e){
        Player p = e.getPlayer();

        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);

        if(e.getItemDrop().getItemStack().equals(prisonPlayer.getPickaxe().getPickaxeItemStack()) || e.getItemDrop().getItemStack().getType().equals(Material.BOOK)){
            e.setCancelled(true);
            return;
        }


    }
}
