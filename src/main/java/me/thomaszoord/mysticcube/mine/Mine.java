package me.thomaszoord.mysticcube.mine;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import me.thomaszoord.mysticcube.Core;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Mine
{

    private Player player;
    private final int blockWidth, blockHeight, blockDepth;
    private final Material blockMaterial;

    public Mine(Player player, int blockWidth, int blockHeight, int blockDepth, Material blockMaterial)
    {
        this.player = player;

        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.blockDepth = blockDepth;
        this.blockMaterial = blockMaterial;

        Core.getProtocolManager().addPacketListener(new PacketAdapter(Core.getPlugin(), ListenerPriority.HIGH,
                PacketType.Play.Server.BLOCK_BREAK_ANIMATION) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                event.getPlayer().sendMessage("Yep");
                System.out.println("YEP");
            }
        });
    }

    public void createCube(Location startPosition)
    {
        for(int x = 0; x < blockWidth; x++)
        {
            for(int y = 0; y < blockHeight; y++)
            {
                for(int z = 0; z < blockDepth; z++){
                    sendPacket(player,
                            startPosition.getBlockX() + x,
                            startPosition.getBlockY() + y,
                            startPosition.getBlockZ() + z);

                    System.out.println(startPosition.getBlockZ() + z);

                }

            }
        }
    }

    private void sendPacket(Player p, int x, int y, int z)  {
        PacketContainer payloadPacket = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);

        payloadPacket.getBlockData().write(0, WrappedBlockData.createData(blockMaterial));
        payloadPacket.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));

        Core.getProtocolManager().sendServerPacket(p, payloadPacket);
    }

}