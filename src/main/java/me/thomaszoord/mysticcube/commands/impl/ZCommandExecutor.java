package me.thomaszoord.mysticcube.commands.impl;

import org.bukkit.entity.Player;

public abstract class ZCommandExecutor {

    private final String cmd;
    private final String perm;
    private final String desc;


    public ZCommandExecutor(String cmd, String perm, String desc){
        this.cmd = cmd;
        this.perm = perm;
        this.desc = desc;
    }

    public ZCommandExecutor(String cmd, String perm){
        this(cmd, "No description avaliable...", perm);
    }

    protected void executeCommand(Player p, String[] args){
        if(!p.hasPermission(getPerm())){
            p.sendMessage("You don't have permission to do that.");
            return;
        }

        onCommand(p, args);
    }

    protected abstract void onCommand(Player p, String[] args);

    public String getCmd() {
        return cmd;
    }

    public String getPerm() {
        return perm;
    }

    public String getDesc() {
        return desc;
    }
}
