package me.thomaszoord.mysticcube.commands.player;


import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.utils.config.Configs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends ZCommand {
    public static Location spawnLocation = Configs.core.getLocation("Spawn");

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }

        Player p = (Player) sender;

        teleportToSpawn(p);
        return true;
    }


    public static void teleportToSpawn(Player p){

        if(p.getAllowFlight()){
            p.setFlying(false);
            p.setAllowFlight(false);
        }

        for(Player pl : Bukkit.getServer().getOnlinePlayers()){
            pl.showPlayer(p);
            p.showPlayer(pl);
        }

        p.teleport(SpawnCommand.spawnLocation);
    }

}
