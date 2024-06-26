package me.thomaszoord.mysticcube.commands.admin.locations;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.commands.player.SpawnCommand;
import me.thomaszoord.mysticcube.utils.config.Configs;
import org.bukkit.entity.Player;

public class AdminSetLocation extends ZCommandExecutor {

    public AdminSetLocation() {

        super("set", "mc.admin", "Set the location from some location");
    }


    @Override
    protected void onCommand(Player p, String[] args) {
        String logError = "§c[MysticCube] Correct use: /admin set <spawn/dungeon/etc>";

        if(args.length == 0){
            p.sendMessage(logError);
            return;
        }

        switch(args[1].toLowerCase()){
            case "spawn":
                SpawnCommand.spawnLocation = p.getLocation();
                p.sendMessage("§a[MysticCube] Spawn sucessfull set!");
                break;
            case "minespawn":
                Configs.core.saveLocation("MineOneSpawn", p.getLocation());

                p.sendMessage("§a[MysticCube] MineOne sucessfull set!");

                break;
            case "mineone":
                Configs.core.saveLocation("MineOne", p.getLocation());
                p.sendMessage("§a[MysticCube] MineOneSpawn sucessfull set!");
            default:
            p.sendMessage(logError);
                break;

        }
    }


}
