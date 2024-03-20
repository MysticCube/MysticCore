package me.thomaszoord.mysticcube.commands.admin.locations;

import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.utils.Configs;
import org.bukkit.entity.Player;

public class AdminSetLocation extends ZCommandExecutor {

    public AdminSetLocation() {

        super("set", "mc.admin", "Set the location from some location");
    }


    @Override
    protected void onCommand(Player p, String[] args) {

        if(args.length == 0){
            p.sendMessage("§c[MysticCube] Correct use: /admin set <spawn/dungeon/etc>");
            return;
        }

        switch(args[1].toLowerCase()){
            case "spawn":
                SpawnCommand.spawnLocation = p.getLocation();
                Configs.core.saveLocation("Spawn", SpawnCommand.spawnLocation);

                p.sendMessage("[MysticCube] Spawn sucessfull set!");
                break;
            case "dungeon":


                break;

        }
    }


}
