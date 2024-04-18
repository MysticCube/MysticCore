package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.fortune;

import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;

import java.util.Arrays;

public class Fortune extends PickaxeEnchantment {


    public Fortune() {
        super(FortuneConfig.name,
                StringUtils.getItemStackByString(FortuneConfig.guiItem),
                FortuneConfig.minimumLevel,
                1,
                FortuneConfig.maxLevel,
                FortuneConfig.price,
                FortuneConfig.baseActivationPercentage,
                FortuneConfig.multiplierPrice,
                Arrays.asList(FortuneConfig.description));
    }


    private final double coinMultiplier = FortuneConfig.coinMultiplier;
    private double buffCoins(){
        double buffCoin = coinMultiplier;
        return buffCoin *= getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        BlockBreakPacket.coins += buffCoins();
    }








}
