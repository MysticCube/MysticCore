package me.thomaszoord.mysticcube.commands.admin;


import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AdminTestCommand extends ZCommandExecutor {
    public AdminTestCommand() {
        super("test", "mc.admin", "Test command for mine creation");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
//        Mine mine = new Mine(p, 10, 10, 10, Material.DIAMOND_BLOCK);
//        mine.createCube(p.getLocation());
    }
}