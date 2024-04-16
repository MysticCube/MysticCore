package me.thomaszoord.mysticcube.commands.mine;

import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand extends ZCommand {


    public MineCommand(){
        super("mine", new MineResetCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;

            if(strings.length == 0){
                MinerGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(p));
                return true;
            }




            if(strings[0].equalsIgnoreCase("reset")){
                PrisonPlayerManager.getPrisonPlayer(p).getMine().resetMine();
                p.sendMessage("§aMine successfully reseted!");
                return true;
            }

            return true;
        }
        return false;
    }
}
