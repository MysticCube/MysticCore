package me.thomaszoord.mysticcube.commands.mine;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.entity.Player;

public class MineResetCommand extends ZCommandExecutor {
    public MineResetCommand() {
        super("reset", "mc.user", "Reset your mine");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
        PrisonPlayerManager.getPrisonPlayer(p).getMine().resetMine();
        p.sendMessage("§aMine successfully reseted!");
    }
}
