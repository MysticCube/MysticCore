package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.velocity;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import org.bukkit.Bukkit;
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
                Arrays.asList(VelocityConfig.description));

    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        Bukkit.getScheduler().runTask(Core.getPlugin(), () -> {
            prisonPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, getLevel(), false, false));
        });    }





}
