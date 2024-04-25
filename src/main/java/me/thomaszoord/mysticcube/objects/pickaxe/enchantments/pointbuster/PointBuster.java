package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.pointbuster;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.Location;

import java.util.Arrays;

public class PointBuster extends PickaxeEnchantment {


    public PointBuster() {
        super(PointBusterConfig.name,
                StringUtils.getItemStackByString(PointBusterConfig.guiItem),
                PointBusterConfig.minimumLevel,
                1,
                PointBusterConfig.maxLevel,
                PointBusterConfig.price,
                PointBusterConfig.baseActivationPercentage,
                PointBusterConfig.multiplierPrice,
                Arrays.asList(PointBusterConfig.description),
                PointBusterConfig.color);

    }


    private double pointMultiplier = PointBusterConfig.pointMultiplier;
    private double buffCoins(){
        double buffCoin = pointMultiplier;
        return buffCoin *= getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock) {
        BlockBreakPacket.points += buffCoins();
    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }


}
