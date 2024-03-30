package me.thomaszoord.mysticcube.listeners.player;

import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticcube.mine.MineBlock;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerBlockBreakEvent implements Listener {

//
//    static List<Material> pickaxe = new ArrayList<>();
//
//     static {
//        pickaxe.add(Material.WOOD_PICKAXE);
//        pickaxe.add(Material.STONE_PICKAXE);
//        pickaxe.add(Material.GOLD_PICKAXE);
//        pickaxe.add(Material.IRON_PICKAXE);
//        pickaxe.add(Material.DIAMOND_PICKAXE);
//    }
//
//    @EventHandler
//    public void breakBlock(BlockBreakEvent event) {
//
//
//            PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(event.getPlayer());
//
//            if(!prisonPlayer.getMine().getLocationHandleMineBlock().containsKey(event.getBlock().getLocation())){
//                event.setCancelled(true);
//                return;
//            }
//
//
//
//            MineBlock mineBlock = prisonPlayer.getMine().getLocationHandleMineBlock().get(event.getBlock().getLocation());
//
//            prisonPlayer.setCoins(mineBlock.getCoins());
//            prisonPlayer.addPoints(mineBlock.getPoints());
//
//            prisonPlayer.getPlayer().sendMessage("Bloco: " + mineBlock.getMaterial());
//            prisonPlayer.getPlayer().sendMessage("Coins: " + mineBlock.getCoins());
//            prisonPlayer.getPlayer().sendMessage("Points: " + mineBlock.getPoints());
//
//            ScoreboardLobby.updateScoreboard(prisonPlayer);
//
//    }



}
