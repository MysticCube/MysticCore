package me.thomaszoord.mysticcube.commands.player;


import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends ZCommand {
    public static Location spawnLocation;

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }

        Player p = (Player) sender;

        p.teleport(spawnLocation);
        return true;
    }

}
