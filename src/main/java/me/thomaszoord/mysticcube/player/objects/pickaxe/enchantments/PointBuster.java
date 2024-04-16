package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PointBuster extends PickaxeEnchantment {


    public PointBuster() {
        super("Point Buster",
                new ItemStack(Material.EXP_BOTTLE, 1),
                5,
                5,
                1000,
                100,
                6,
                "§7Have a chance to turn an entire",
                "§7layer into blocks of emerald!");

    }


    private double pointMultiplier = 10;
    private double buffCoins(){
        double buffCoin = pointMultiplier;
        return buffCoin *= getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        BlockBreakPacket.points += buffCoins();
    }





}
