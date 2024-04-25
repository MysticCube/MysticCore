package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.emeraldmine;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.mine.Mine;
import me.thomaszoord.mysticcube.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;

public class EmeraldMine extends PickaxeEnchantment {

    public EmeraldMine() {
        super(EmeraldMineConfig.name,
                ItemBuilder.skull().texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWYxMjFmN2MxYWIxNTY3ZmYyMTk4M2ZmN2E5ZTU1YzQwYzBiODY1ZjA1MGQzN2U1ZDM1ZGVmYmFhIn19fQ==").build(),
                EmeraldMineConfig.minimumLevel,
                0,
                EmeraldMineConfig.maxLevel,
                EmeraldMineConfig.price,
                EmeraldMineConfig.baseActivationPercentage,
                EmeraldMineConfig.multiplierPrice,
                Arrays.asList(EmeraldMineConfig.description),
                EmeraldMineConfig.color);
    }



    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location location) {

        MineBlock emeraldBlock = new MineBlock(Material.EMERALD_BLOCK, 2.0, 2.0, 5.0);

        if (shouldActivate()) {

            String enchantmentName = getColor() + getName();

            prisonPlayer.getPlayer().sendMessage(" ");
            prisonPlayer.getPlayer().sendMessage(StringUtils.getCenteredMessage("§d§lGG!", 100));
            prisonPlayer.getPlayer().sendMessage(" §7The " + ChatColor.translateAlternateColorCodes('&', enchantmentName)
                    + " §7enchantment has been activated.");
            prisonPlayer.getPlayer().sendMessage(" ");

            prisonPlayer.getPlayer().playSound(prisonPlayer.getPlayer().getLocation(), Sound.LEVEL_UP, 0.5f, 0.5f);
            int minedBlockY = location.getBlockY();

            int centerX = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockX() + (prisonPlayer.getMine().getMineRank().getMineSize() / 2);
            int centerZ = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockZ() + (prisonPlayer.getMine().getMineRank().getMineSize() / 2);

            int mineWidth = prisonPlayer.getMine().getMineRank().getMineSize();

            for (int x = centerX - (mineWidth / 2); x <= centerX + (mineWidth / 2); x++) {
                for (int z = centerZ - (mineWidth / 2); z <= centerZ + (mineWidth / 2); z++) {
                    Location blockLocation = new Location(location.getWorld(), x, minedBlockY, z);
                    prisonPlayer.getMine().getLocationHandleMineBlock().remove(blockLocation);

                    Mine.sendPacket(prisonPlayer.getPlayer(), x, minedBlockY, z, Material.EMERALD_BLOCK);
                    prisonPlayer.getMine().getLocationHandleMineBlock().put(blockLocation, emeraldBlock);

                }
            }
        }
    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }
}
