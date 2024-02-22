package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.utils.colorapi.ColorAPI;
import org.bukkit.entity.Player;

public class AdminTestCommand extends ZCommandExecutor {


    public AdminTestCommand() {
        super("test", "mc.admin", "Test command for gradient message.");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
        p.sendMessage(ColorAPI.colorizeGradient("{#ff00dd>}Texto insano em portugues brasileiro{#0062ff<}"));
        p.sendMessage();
    }
}
