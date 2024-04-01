package me.thomaszoord.mysticcube.player.objects.mine.mineblock;

import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;

import java.util.ArrayList;
import java.util.List;

public class MineLevel {

    public int minLevel;
    public int maxLevel;

    private List<MineBlock> blocks;

    public MineLevel(int startRange, int endRange) {
        this.minLevel = startRange;
        this.maxLevel = endRange;
        this.blocks = new ArrayList<>();
    }


    public void addBlock(MineBlock block) {
        blocks.add(block);
    }

    public List<MineBlock> getBlocks() {
        return blocks;
    }

    public boolean isInRange(int level) {
        return level >= minLevel && level <= maxLevel;
    }
}
