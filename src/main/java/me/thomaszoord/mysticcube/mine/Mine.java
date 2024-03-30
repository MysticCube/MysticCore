package me.thomaszoord.mysticcube.mine;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Mine {

    private PrisonPlayer owner;
    private MineSize mineType;

    private HashMap<Location, MineBlock> locationHandledMineBlocks;


    public Mine(PrisonPlayer owner, MineSize mineType) {
        this.owner = owner;
        this.mineType = mineType;
    }

    public void teleportToMine(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 5, false, false));

        for(Player pl : this.mineType.getCenterLocation().getWorld().getPlayers()){
            pl.hidePlayer(p);
            p.hidePlayer(pl);
        }

        if(!p.getAllowFlight()){
            p.setAllowFlight(true);
        }

        p.teleport(getMineType().getSpawnMine());


        new BukkitRunnable(){
            @Override
            public void run(){
                owner.getMine().createCube();
            }
        }.runTaskLaterAsynchronously(Core.getPlugin(), 50L);
    }



    public void createCube(){
        int size = mineType.getMineSize();

        Random random = new Random();

        //Create a new HashMap to create the cube
        locationHandledMineBlocks = new HashMap<>();

        //Algorithm to check the player level with the correct list of the blocks to the Mine.
        List<MineBlock> blocks = BlocksMapping.findMineLevelForPlayer(owner).getBlocks();

        if (blocks.isEmpty()) {
            System.err.println("Error: No mine blocks found for player.");
            return;
        }


        Collections.shuffle(blocks);



        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < 39; y++)
            {
                for(int z = 0; z < size; z++){

                    //Get a random block from the MineBlock list
                    MineBlock block = blocks.get(random.nextInt(blocks.size()));

                    if(block.getDurability() != 0){
                        sendPacket(owner.getPlayer(),
                                mineType.getCenterLocation().getBlockX() + x,
                                mineType.getCenterLocation().getBlockY() + y,
                                mineType.getCenterLocation().getBlockZ() + z,
                                block.getMaterial(), block.getDurability());

                    } else {
                        sendPacket(owner.getPlayer(),
                                mineType.getCenterLocation().getBlockX() + x,
                                mineType.getCenterLocation().getBlockY() + y,
                                mineType.getCenterLocation().getBlockZ() + z,
                                block.getMaterial());
                    }



                    Location location = new Location(mineType.getCenterLocation().getWorld(), mineType.getCenterLocation().getBlockX() + x, mineType.getCenterLocation().getBlockY() + y, mineType.getCenterLocation().getBlockZ() + z);
                    locationHandledMineBlocks.put(location, block);

                }

            }
        }
    }
    private void sendPacket(Player p, int x, int y, int z, Material blockMaterial)  {
        PacketContainer payloadPacket = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);

        payloadPacket.getBlockData().write(0, WrappedBlockData.createData(blockMaterial));
        payloadPacket.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));

        Core.getProtocolManager().sendServerPacket(p, payloadPacket);
    }

    private void sendPacket(Player p, int x, int y, int z, Material blockMaterial, short data)  {
        PacketContainer payloadPacket = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);

        payloadPacket.getBlockData().write(0, WrappedBlockData.createData(blockMaterial, data));
        payloadPacket.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));

        Core.getProtocolManager().sendServerPacket(p, payloadPacket);
    }
    


    public HashMap<Location, MineBlock> getLocationHandleMineBlock() {
        return locationHandledMineBlocks;
    }

    public PrisonPlayer getOwner() {
        return owner;
    }

    public MineSize getMineType() {
        return mineType;
    }
}
