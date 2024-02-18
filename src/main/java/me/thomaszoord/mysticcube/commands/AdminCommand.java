package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;

public class AdminCommand extends ZCommand {
    public AdminCommand() {
        super("admin",
                new SetMinerCommand());
    }
}
