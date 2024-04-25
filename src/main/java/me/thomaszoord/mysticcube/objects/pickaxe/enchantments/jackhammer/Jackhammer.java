package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.jackhammer;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.objects.mine.Mine;
import me.thomaszoord.mysticcube.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.utils.StringUtils;
import me.thomaszoord.mysticcube.utils.packets.ParticleAPI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Jackhammer extends PickaxeEnchantment {

    public Jackhammer() {
        super(JackhammerConfig.name,
                StringUtils.getItemStackByString(JackhammerConfig.guiItem),
                JackhammerConfig.minimumLevel,
                0,
                JackhammerConfig.maxLevel,
                JackhammerConfig.price,
                JackhammerConfig.baseActivationPercentage,
                JackhammerConfig.multiplierPrice,
                Arrays.asList(JackhammerConfig.description),
                JackhammerConfig.color);
    }




    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location location) {


        if (shouldActivate()) {
            String enchantmentName = getColor() + getName();


            prisonPlayer.getPlayer().sendMessage(" ");
            prisonPlayer.getPlayer().sendMessage(StringUtils.getCenteredMessage("§d§lGG!", 100));
            prisonPlayer.getPlayer().sendMessage(" §7The " + ChatColor.translateAlternateColorCodes('&', enchantmentName)
                    + " §7enchantment has been activated.");
            prisonPlayer.getPlayer().sendMessage(" ");

            prisonPlayer.getPlayer().playSound(prisonPlayer.getPlayer().getLocation(), Sound.LEVEL_UP, 0.5f, 0.2f);



            int minedBlockY = location.getBlockY();

            int centerX = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockX() + (prisonPlayer.getMine().getMineRank().getMineSize() / 2);
            int centerZ = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockZ() + (prisonPlayer.getMine().getMineRank().getMineSize() / 2);

            int mineWidth = prisonPlayer.getMine().getMineRank().getMineSize();

            for (int x = centerX - (mineWidth / 2); x <= centerX + (mineWidth / 2); x++) {
                for (int z = centerZ - (mineWidth / 2); z <= centerZ + (mineWidth / 2); z++) {
                    Location blockLocation = new Location(location.getWorld(), x, minedBlockY, z);
                    addPoints(prisonPlayer, blockLocation);

                    ItemMeta pickaxe = prisonPlayer.getPlayer().getInventory().getItem(0).getItemMeta();
                    pickaxe.setLore(prisonPlayer.getPickaxe().pickaxeLore());
                    prisonPlayer.getPlayer().getInventory().getItem(0).setItemMeta(pickaxe);

                }
            }
        }
    }


    public void addPoints(PrisonPlayer prisonPlayer, Location location){
        if(prisonPlayer.getMine().getLocationHandleMineBlock() == null) return;

        if (!prisonPlayer.getMine().getLocationHandleMineBlock().containsKey(location)) {
            return;
        }

        MineBlock mineBlock = prisonPlayer.getMine().getLocationHandleMineBlock().get(location);


        BlockBreakPacket.coins = mineBlock.getCoins();
        BlockBreakPacket.points = mineBlock.getPoints();
        BlockBreakPacket.tokens = mineBlock.getTokens();


        prisonPlayer.getMine().getLocationHandleMineBlock().remove(location);
        prisonPlayer.getPickaxe().setMinedBlocks(prisonPlayer.getPickaxe().getMinedBlocks() + 1);

        prisonPlayer.setCoins(BlockBreakPacket.coins);
        prisonPlayer.addPoints(BlockBreakPacket.points);
        prisonPlayer.addTokens(BlockBreakPacket.tokens);

        Mine.sendPacket(prisonPlayer.getPlayer(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), Material.AIR);
        ParticleAPI.sendBlockBreakAnimation(prisonPlayer.getPlayer(), location, mineBlock);
        ParticleAPI.sendParticles(prisonPlayer.getPlayer(), location);


    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }
}
