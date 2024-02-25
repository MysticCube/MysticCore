package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import org.bukkit.entity.Player;

public class AdminTestCommand extends ZCommandExecutor {


    public AdminTestCommand() {
        super("test", "mc.admin", "Test command for gradient message.");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
        if(p.getAllowFlight() == false){
            p.setAllowFlight(true);
            p.setFlying(true);
        }
    }
}
