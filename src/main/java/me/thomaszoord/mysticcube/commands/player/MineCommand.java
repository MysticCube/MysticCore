package me.thomaszoord.mysticcube.commands.player;

import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand extends ZCommand {


    public MineCommand(){
        super("mine");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){

            Player p = (Player) commandSender;
            MinerGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(p));
            return true;
        }
        return false;
    }
}
