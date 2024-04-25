package me.thomaszoord.mysticcube.objects.mine;

import me.thomaszoord.mysticcube.utils.config.Configs;
import org.bukkit.Location;

public enum MineRank {



    RANK_1(11, 1,

            Configs.core.getLocation("MineOne"),
            Configs.core.getLocation("MineOneSpawn")),
    RANK_2(16, 2,
            Configs.core.getLocation("MineTwo"),
            Configs.core.getLocation("MineTwoSpawn")),
    RANK_3(21, 3,
            Configs.core.getLocation("MineThree"),
            Configs.core.getLocation("MineThreeSpawn"));


    private int mineSize;
    private int level;
    private Location spawnMine;
    private Location centerLocation;

    MineRank(int mineSize, int level, Location spawnMine, Location centerLocation) {
        this.mineSize = mineSize;
        this.level = level;
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
