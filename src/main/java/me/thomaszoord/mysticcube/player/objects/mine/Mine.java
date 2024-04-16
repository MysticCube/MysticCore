package me.thomaszoord.mysticcube.player.objects.mine;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.mine.enums.MineConfigs;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.BlocksMapping;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Mine {

    private Set<String> ignoreTabList = new HashSet<>();
    private PrisonPlayer owner;
    private MineConfigs mineType;

    private HashMap<Location, MineBlock> locationHandledMineBlocks;


    public Mine(PrisonPlayer owner, MineConfigs mineType) {
        this.owner = owner;
        this.mineType = mineType;
    }

    public void teleportToMine(){
        getOwner().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 5, false, false));

//        PlayerTabListPlayerInfo.ignore(owner.getPlayer());;

        for(Player pl : this.mineType.getCenterLocation().getWorld().getPlayers()){
            pl.hidePlayer(owner.getPlayer());
            owner.getPlayer().hidePlayer(pl);
        }

        if(!owner.getPlayer().getAllowFlight()){
            owner.getPlayer().setAllowFlight(true);
        }

//        PlayerTabListPlayerInfo.unignore(owner.getPlayer());;

        owner.getPlayer().teleport(getMineType().getSpawnMine());


        new BukkitRunnable(){
            @Override
            public void run(){
                owner.getMine().createCube();
            }
        }.runTaskLaterAsynchronously(Core.getPlugin(), 30L);
    }

    public void resetMine(){
        double centerX = getMineType().getCenterLocation().getX() + (getMineType().getMineSize() / 2);
        double centerY = getMineType().getCenterLocation().getY() + 42;
        double centerZ = getMineType().getCenterLocation().getZ() + (getMineType().getMineSize() / 2);


        Location location = new Location(getMineType().getCenterLocation().getWorld(), centerX, centerY, centerZ, 50, 0);;
        getOwner().getPlayer().teleport(location);

        createCube();

    }



    public void createCube(){
        int size = mineType.getMineSize();

        Random random = new Random();

        locationHandledMineBlocks = new HashMap<>();

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

    public MineConfigs getMineType() {
        return mineType;
    }
}
