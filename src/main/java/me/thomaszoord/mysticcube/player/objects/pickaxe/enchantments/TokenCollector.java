package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TokenCollector extends PickaxeEnchantment {


    public TokenCollector() {
        super("Token Collector",
                new ItemStack(Material.INK_SACK, 1, (short) 11),
                1,
                5,
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
        return buffCoin * getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer) {
        BlockBreakPacket.tokens += buffCoins();
    }





}
