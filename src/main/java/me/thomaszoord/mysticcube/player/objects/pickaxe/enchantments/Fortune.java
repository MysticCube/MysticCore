package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Fortune extends PickaxeEnchantment {


    public Fortune() {
        super("Fortune",
                new ItemStack(Material.PAPER, 1),
                1,
                900,
                1000,
                0.102,
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
        BlockBreakPacket.coins += buffCoins();
    }





}
