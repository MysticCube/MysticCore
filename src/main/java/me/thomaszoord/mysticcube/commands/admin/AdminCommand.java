package me.thomaszoord.mysticcube.commands.admin;

import me.thomaszoord.mysticcube.commands.admin.locations.AdminSetLocation;
import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.commands.admin.npcs.AdminDungeonCommand;
import me.thomaszoord.mysticcube.commands.admin.npcs.AdminMinerCommand;
import me.thomaszoord.mysticcube.commands.admin.npcs.AdminPlotCommand;
import me.thomaszoord.mysticcube.commands.admin.npcs.AdminSpawnerCommand;

public class AdminCommand extends ZCommand {
    public AdminCommand() {
        super("admin",
                new AdminMinerCommand(),
                new AdminDungeonCommand(),
                new AdminSpawnerCommand(),
                new AdminTestCommand(),
                new AdminPlotCommand(),
                new AdminSetLocation()
        );

    }




}
