package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommand;

public class AdminCommand extends ZCommand {
    public AdminCommand() {
        super("admin",
                new AdminMinerCommand(),
                new AdminTestCommand(),
                new AdminDungeonCommand());
    }
}
