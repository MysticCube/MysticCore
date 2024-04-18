package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.pointbuster;

import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
                Arrays.asList(PointBusterConfig.description));

    }


    private double pointMultiplier = PointBusterConfig.pointMultiplier;
    private double buffCoins(){
        double buffCoin = pointMultiplier;
        return buffCoin *= getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        BlockBreakPacket.points += buffCoins();
    }





}
