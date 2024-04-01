package me.thomaszoord.mysticcube.player.objects.mine.mineblock;

import org.bukkit.Material;

public class MineBlock {


    private final Material material;
    private short durability;
    private final double points;
    private final double coins;


    public MineBlock(Material material, short durability, double points, double coins) {
        this.material = material;
        this.durability = durability;
        this.points = points;
        this.coins = coins;
    }

    public MineBlock(Material material, double points, double coins) {
        this.material = material;
        this.points = points;
        this.coins = coins;
    }

    public Material getMaterial() {
        return material;
    }

    public short getDurability() {
        return durability;
    }

    public double getPoints() {
        return points;
    }

    public double getCoins() {
        return coins;
    }
}
