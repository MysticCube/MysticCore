package me.thomaszoord.mysticcube.player.objects.mine.enums;

import me.thomaszoord.mysticcube.utils.Configs;
import org.bukkit.Location;

public enum MineSize {


    RANK_1(11, Configs.core.getLocation("MineOne"), Configs.core.getLocation("MineOneSpawn")),
    RANK_2(15, Configs.core.getLocation("MineTwo"), Configs.core.getLocation("MineTwoSpawn")),
    RANK_3(20, Configs.core.getLocation("MineThree"), Configs.core.getLocation("MineThreeSpawn"));


    private int mineSize;
    private Location spawnMine;
    private Location centerLocation;

    MineSize(int mineSize, Location spawnMine, Location centerLocation) {
        this.mineSize = mineSize;
        this.spawnMine = spawnMine;
        this.centerLocation = centerLocation;
    }

    public Location getSpawnMine() {
        return spawnMine;
    }

    public Location getCenterLocation() {
        return centerLocation;
    }

    public int getMineSize() {
        return mineSize;
    }
}
