package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.laser;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.mine.Mine;
import me.thomaszoord.mysticcube.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import me.thomaszoord.mysticcube.utils.packets.ParticleAPI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Laser extends PickaxeEnchantment {

    public Laser() {
        super(LaserConfig.name,
                StringUtils.getItemStackByString(LaserConfig.guiItem),
                LaserConfig.minimumLevel,
                0,
                LaserConfig.maxLevel,
                LaserConfig.price,
                LaserConfig.baseActivationPercentage,
                LaserConfig.multiplierPrice,
                Arrays.asList(LaserConfig.description),
                LaserConfig.color);
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

            int mineWidth = prisonPlayer.getMine().getMineRank().getMineSize();
            int mineHeight = 39;

            int centerX = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockX();

            int startY = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockY();

            int x = centerX;

            while (x <= centerX + mineWidth) {
                int y = startY;
                while (y < startY + mineHeight) {
                    Location blockLocation = new Location(location.getWorld(), x, y, location.getBlockZ());
                    addPoints(prisonPlayer, blockLocation);
                    y++;
                }
                x++;
            }


            ItemMeta pickaxe = prisonPlayer.getPlayer().getInventory().getItem(0).getItemMeta();
            pickaxe.setLore(prisonPlayer.getPickaxe().pickaxeLore());
            prisonPlayer.getPlayer().getInventory().getItem(0).setItemMeta(pickaxe);
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
        ParticleAPI.sendParticles(prisonPlayer.getPlayer(), location);


    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }
}
