package me.thomaszoord.mysticcube.listeners.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class BlockInteractPacket extends PacketAdapter {


    public BlockInteractPacket(Plugin plugin) {
        super(plugin, ListenerPriority.HIGH, PacketType.Play.Client.BLOCK_PLACE);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Player player = event.getPlayer();
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(player);


        if (event.getPacket().getType().equals(PacketType.Play.Client.BLOCK_PLACE)) {
            BlockPosition blockPosition = event.getPacket().getBlockPositionModifier().read(0);

            event.setCancelled(true);

        }
    }
}
