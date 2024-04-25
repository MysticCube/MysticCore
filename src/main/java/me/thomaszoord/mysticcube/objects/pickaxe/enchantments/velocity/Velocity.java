package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.velocity;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class Velocity extends PickaxeEnchantment {


    public Velocity() {
        super(VelocityConfig.name,
                StringUtils.getItemStackByString(VelocityConfig.guiItem),
                VelocityConfig.minimumLevel,
                1,
                VelocityConfig.maxLevel,
                VelocityConfig.price,
                VelocityConfig.baseActivationPercentage,
                VelocityConfig.multiplierPrice,
                Arrays.asList(VelocityConfig.description),
                VelocityConfig.color);

    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock) {
        Bukkit.getScheduler().runTask(Core.getPlugin(), () -> {
            prisonPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, getLevel(), false, false));
        });    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }


}
