package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.tokencollector;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.Location;

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
                Arrays.asList(TokenCollectorConfig.description),
                TokenCollectorConfig.color);

    }


    private double tokenMultiplier = TokenCollectorConfig.tokenMultiplier;
    private double buffTokens(){
        double buffCoin = tokenMultiplier;
        return buffCoin * getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock) {
        BlockBreakPacket.tokens += buffTokens();
    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }


}
