package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.Pickaxe;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Fortune;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.PointBuster;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.TokenCollector;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Velocity;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class EnchantmentsManager {

    public static ArrayList<PickaxeEnchantment> enchantments = new ArrayList<>();

    public static Fortune fortune = new Fortune();
    public static Velocity velocity = new Velocity();
    public static PointBuster pointBuster = new PointBuster();
    public static TokenCollector tokenCollector = new TokenCollector();

    static {
        enchantments.add(fortune);
        enchantments.add(velocity);
        enchantments.add(tokenCollector);
        enchantments.add(pointBuster);
    }



}
