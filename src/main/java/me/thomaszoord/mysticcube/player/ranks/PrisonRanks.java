package me.thomaszoord.mysticcube.player.ranks;

public enum PrisonRanks {

     DIRT(3, "Dirt"),
    STONE(3, "Stone"),
    COAL(3, "Coal"),
    LAPIS_LAZULI(3, "Lapis-Lazuli"),
    IRON(3, "Iron"),
    GOLD(3, "Gold"),
    REDSTONE(3, "Redstone"),
    DIAMOND(3, "Diamond"),
    EMERALD(3, "Emerald"),
    NETHERITE(1, "Netherite");



    private final int level;
    private final String name;

    PrisonRanks(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
