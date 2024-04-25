package me.thomaszoord.mysticcube.utils.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.thomaszoord.mysticcube.objects.mine.mineblock.MineBlock;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public class ParticleAPI {



    public static void sendBlockBreakAnimation(Player p, Location location, MineBlock mineBlock) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);

        packet.getIntegers().write(0, 0);

        packet.getBlockPositionModifier().write(0, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ())); //
        packet.getIntegers().write(1, mineBlock.getMaterial().getId());

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }


    public static void sendParticles(Player p, Location loc) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.WORLD_PARTICLES);

        Color color = Color.fromBGR(ThreadLocalRandom.current().nextInt(255),
                ThreadLocalRandom.current().nextInt(255),
                ThreadLocalRandom.current().nextInt(255));

        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, EnumWrappers.Particle.REDSTONE);
        packet.getFloat().write(0, (float) loc.getX()).write(1, (float) loc.getY()).write(2, (float) loc.getZ())
                .write(3, (float)(color.getRed()/255)).write(4, (float) color.getGreen()).write(5, (float) color.getBlue())
                .write(6,1F);

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }
    public static void sendExplosionParticle(Player p, Location loc) {
        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.WORLD_PARTICLES);

        Color color = Color.fromBGR(ThreadLocalRandom.current().nextInt(255),
                ThreadLocalRandom.current().nextInt(255),
                ThreadLocalRandom.current().nextInt(255));

        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, EnumWrappers.Particle.EXPLOSION_HUGE);
        packet.getFloat().write(0, (float) loc.getX()).write(1, (float) loc.getY()).write(2, (float) loc.getZ())
                .write(3, (float)(color.getRed()/255)).write(4, (float) color.getGreen()).write(5, (float) color.getBlue())
                .write(6,3F);

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }



}
