package me.thomaszoord.mysticcube.objects.mine.mineblock;

import org.bukkit.Material;

public class MineBlock {
    private final Material material;
    private short durability;
    private double points;
    private double coins;
    private double tokens;


    public MineBlock(Material material, short durability, double points, double coins, double tokens) {
        this.material = material;
        this.durability = durability;
        this.points = points;
        this.coins = coins;
        this.tokens = tokens;
    }

    public MineBlock(Material material, double points, double coins, double tokens) {
        this.material = material;
        this.points = points;
        this.coins = coins;
        this.tokens = tokens;

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

    public void setPoints(double points) {
        this.points = points;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public void setDurability(short durability) {
        this.durability = durability;
    }

    public double getTokens() {
        return tokens;
    }

    public void setTokens(double tokens) {
        this.tokens = tokens;
    }
}
