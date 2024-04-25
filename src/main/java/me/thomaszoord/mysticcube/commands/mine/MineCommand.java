package me.thomaszoord.mysticcube.commands.mine;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.commands.impl.ZCommand;
import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class MineCommand extends ZCommand {

    HashMap<Player, BukkitRunnable> resetCooldown = new HashMap<>();

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
                resetMine(p);
                return true;
            }

            if(strings[0].equalsIgnoreCase("go")){
                PrisonPlayerManager.getPrisonPlayer(p).getMine().teleportToMine();
                return true;
            }

            return true;
        }
        return false;
    }


    public void resetMine(Player p){


        if(resetCooldown.containsKey(p)){
            p.sendMessage("§cWait §f10s §cbefore using the mine reset again");
            return;
        }

        resetCooldown.put(p, new BukkitRunnable() {

            public int counter = 10;
            @Override
            public void run() {
                if(counter == 0){
                    resetCooldown.remove(p);

                }

                counter--;
            }


        });
        resetCooldown.get(p).runTaskLater(Core.getPlugin(), 10 * 20L);

        PrisonPlayerManager.getPrisonPlayer(p).getMine().resetMine();
        p.sendMessage("§aMine successfully reseted!");
    }
}
