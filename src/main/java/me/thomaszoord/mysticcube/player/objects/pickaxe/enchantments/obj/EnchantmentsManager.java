package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj;

import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.fortune.Fortune;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.pointbuster.PointBuster;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.tokencollector.TokenCollector;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.velocity.Velocity;

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
