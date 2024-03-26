package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public abstract class APickaxeEnchantment {

    protected final String name;
    protected int level = 0;

    public APickaxeEnchantment(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public abstract void onBreakBlockEvent(PrisonPlayer prisonPlayer);

    public void enchantmentLevelUPEvent(Player p){
        level++;
        p.sendMessage("level up! the enchantment " + name + " was upgraded!");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
