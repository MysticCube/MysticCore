package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import org.bukkit.entity.Player;

public class SetMinerCommand extends ZCommandExecutor {



    public SetMinerCommand() {
        super("miner", "mc.admin", "Set the mine npc for teleport");
    }

    @Override
    protected void onCommand(Player p, String[] args) {


    }
}
