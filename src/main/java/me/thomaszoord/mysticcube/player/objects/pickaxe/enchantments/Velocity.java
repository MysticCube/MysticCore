package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Velocity extends PickaxeEnchantment {


    public Velocity() {
        super("Velocity", new ItemStack(Material.IRON_BOOTS, 1),
                20,
                3,
                5,
                1000,
                100,
                6,
                "§7Have a chance to turn an entire",
                "§7layer into blocks of emerald!");

    }


    private double coinMultiplier = 10;
    private double buffCoins(){
        double buffCoin = coinMultiplier;
        return buffCoin *= getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        Bukkit.getScheduler().runTask(Core.getPlugin(), () -> {
            prisonPlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 90, getLevel(), false, false));
        });    }





}
