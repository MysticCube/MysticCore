package me.thomaszoord.mysticcube.listeners.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticcube.mine.MineBlock;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

import static com.comphenix.protocol.wrappers.EnumWrappers.PlayerDigType.ABORT_DESTROY_BLOCK;
import static com.comphenix.protocol.wrappers.EnumWrappers.PlayerDigType.START_DESTROY_BLOCK;


public class BlockBreakPacket extends PacketAdapter {

    public BlockBreakPacket(Plugin plugin) {
        super(plugin, ListenerPriority.HIGH, PacketType.Play.Client.BLOCK_DIG);
    }



    static List<Material> pickaxe = new ArrayList<>();

    static {
        pickaxe.add(Material.WOOD_PICKAXE);
        pickaxe.add(Material.STONE_PICKAXE);
        pickaxe.add(Material.GOLD_PICKAXE);
        pickaxe.add(Material.IRON_PICKAXE);
        pickaxe.add(Material.DIAMOND_PICKAXE);
    }




    @Override
    public void onPacketReceiving(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        Player player = event.getPlayer();

        EnumWrappers.PlayerDigType type = packet.getPlayerDigTypes().read(0);
        if (type == START_DESTROY_BLOCK) {

            if(pickaxe.contains(event.getPlayer().getItemInHand().getType())){
                event.setCancelled(true);
                return;
            }

            BlockPosition blockPosition = packet.getBlockPositionModifier().read(0);
            World world = player.getWorld();
            int x = blockPosition.getX();
            int y = blockPosition.getY();
            int z = blockPosition.getZ();
            Location location = new Location(world, x, y, z);

            PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(player);

            if (!prisonPlayer.getMine().getLocationHandleMineBlock().containsKey(location)) {
                event.setCancelled(true);
                return;
            }

            MineBlock mineBlock = prisonPlayer.getMine().getLocationHandleMineBlock().get(location);

            prisonPlayer.setCoins(mineBlock.getCoins());
            prisonPlayer.addPoints(mineBlock.getPoints());

            ScoreboardLobby.updateScoreboard(prisonPlayer);
        }
    }
}

