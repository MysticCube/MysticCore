package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.nuker;

import dev.triumphteam.gui.builder.item.ItemBuilder;
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

public class Nuker extends PickaxeEnchantment {

    public Nuker() {
        super(NukerConfig.name,
                ItemBuilder.skull().texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FmNTk3NzZmMmYwMzQxMmM3YjU5NDdhNjNhMGNmMjgzZDUxZmU2NWFjNmRmN2YyZjg4MmUwODM0NDU2NWU5In19fQ==").build(),
                NukerConfig.minimumLevel,
                0,
                NukerConfig.maxLevel,
                NukerConfig.price,
                NukerConfig.baseActivationPercentage,
                NukerConfig.multiplierPrice,
                Arrays.asList(NukerConfig.description),
                NukerConfig.color);
    }

    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location location) {
        if (shouldActivate()) {
            String enchantmentName = getColor() + getName();

            prisonPlayer.getPlayer().sendMessage(" ");
            prisonPlayer.getPlayer().sendMessage(StringUtils.getCenteredMessage("§c§lBOOM!", 100));
            prisonPlayer.getPlayer().sendMessage(" §7The " + ChatColor.translateAlternateColorCodes('&', enchantmentName)
                    + " §7enchantment has been activated.");
            prisonPlayer.getPlayer().sendMessage(" ");

            prisonPlayer.getPlayer().playSound(prisonPlayer.getPlayer().getLocation(), Sound.LEVEL_UP, 0.5f, 0.2f);

            int blockx = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockX();
            int blockz = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockZ();
            int blocky = prisonPlayer.getMine().getMineRank().getCenterLocation().getBlockY();

            int mineWidth = prisonPlayer.getMine().getMineRank().getMineSize();

            for (int x = 0; x < mineWidth; x++) {
                for (int y = 0; y < 39; y++) {
                    for (int z = 0; z < mineWidth; z++) {
                        Location blockLocation = new Location(location.getWorld(), blockx + x, blocky + y, blockz + z);
                        addPoints(prisonPlayer, blockLocation);
                    }
                }
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
        ParticleAPI.sendExplosionParticle(prisonPlayer.getPlayer(), location);


    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }
}
