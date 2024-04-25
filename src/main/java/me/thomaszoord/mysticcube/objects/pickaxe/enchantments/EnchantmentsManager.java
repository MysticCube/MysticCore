package me.thomaszoord.mysticcube.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.emeraldmine.EmeraldMine;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.fortune.Fortune;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.jackhammer.Jackhammer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.keyscollector.KeysCollector;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.laser.Laser;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.nuker.Nuker;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.pointbuster.PointBuster;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.tokencollector.TokenCollector;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.velocity.Velocity;

import java.util.ArrayList;

public class EnchantmentsManager {

    public static ArrayList<PickaxeEnchantment> enchantments = new ArrayList<>();

    public static Fortune fortune = new Fortune();
    public static Velocity velocity = new Velocity();
    public static PointBuster pointBuster = new PointBuster();
    public static TokenCollector tokenCollector = new TokenCollector();
    public static KeysCollector keysCollector = new KeysCollector();

    public static EmeraldMine emeraldMine = new EmeraldMine();
    public static Laser laser = new Laser();
    public static Jackhammer jackhammer = new Jackhammer();
    public static Nuker nuker = new Nuker();


    static {
        enchantments.add(fortune);
        enchantments.add(velocity);
        enchantments.add(tokenCollector);
        enchantments.add(pointBuster);
        enchantments.add(keysCollector);
        enchantments.add(emeraldMine);
        enchantments.add(laser);
        enchantments.add(jackhammer);
        enchantments.add(nuker);
    }



}
