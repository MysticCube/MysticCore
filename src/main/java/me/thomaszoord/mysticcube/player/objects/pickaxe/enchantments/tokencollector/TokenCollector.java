package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.tokencollector;

import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;

import java.util.Arrays;

public class TokenCollector extends PickaxeEnchantment {


    public TokenCollector() {
        super(TokenCollectorConfig.name,
                StringUtils.getItemStackByString(TokenCollectorConfig.guiItem),
                TokenCollectorConfig.minimumLevel,
                1,
                TokenCollectorConfig.maxLevel,
                TokenCollectorConfig.price,
                TokenCollectorConfig.baseActivationPercentage,
                TokenCollectorConfig.multiplierPrice,
                Arrays.asList(TokenCollectorConfig.description));

    }


    private double tokenMultiplier = TokenCollectorConfig.tokenMultiplier;
    private double buffTokens(){
        double buffCoin = tokenMultiplier;
        return buffCoin * getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        BlockBreakPacket.tokens += buffTokens();
    }





}
