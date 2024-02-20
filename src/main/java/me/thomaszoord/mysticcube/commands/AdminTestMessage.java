package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.utils.ZUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class AdminTestMessage extends ZCommandExecutor {

    public AdminTestMessage()  {
        super("test", "mc.admin", "Test message to ChatColor");
    }


    @Override
    protected void onCommand(Player p, String[] args) {
        p.sendMessage(ZUtils.sendGradientMessage("Mensagem de teste", ChatColor.RED, ChatColor.YELLOW));
    }
}
