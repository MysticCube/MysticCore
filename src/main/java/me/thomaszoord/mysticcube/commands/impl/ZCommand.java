package me.thomaszoord.mysticcube.commands.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ZCommand implements CommandExecutor, TabCompleter {
    private final static int COMMAND_LIST_LIMIT = 8;

    private Map<String, ZCommandExecutor> commandMap;
    private Map<Integer, List<String>> helpMap;

    public ZCommand(String parentCommand, ZCommandExecutor... commands){
        this.commandMap = new HashMap<>();
        this.helpMap = new HashMap<>();

        int commandIndex = 0;
        int page = 0;

        helpMap.put(page, new ArrayList<>());

        for(ZCommandExecutor command : commands){

            if(commandIndex == COMMAND_LIST_LIMIT){
                page++;
                commandIndex = 0;

                helpMap.put(page, new ArrayList<>());
            }

            helpMap.get(page).add("§d/" + parentCommand + " " + command.getCmd() + " §f| " + command.getDesc());
            commandMap.put(command.getCmd(), command);

            commandIndex++;
        }
    }

    public ZCommand(String parentCommand){
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }


        Player p = (Player) sender;


        if(args.length == 0){
            helpHandler(p, s, 0);
            return true;
        }


        String subcommand = args[0].toLowerCase();

        if(subcommand.equals("help")){
            if(args.length == 1){
                helpHandler(p, s, 0);
                return true;
            }

            int page = 0;

            try{
                page = Integer.parseInt(args[1]);
            } catch(Exception e) {
                p.sendMessage("Invalid page.");
                helpHandler(p, s, 0);

                return true;
            }


            if(helpMap.size() < page + 1){
                p.sendMessage("Invalid page.");
                helpHandler(p, s, 0);

                return true;
            }

            helpHandler(p, s, page - 1);



            return true;
        }


        if(commandMap.containsKey(subcommand)){
            ZCommandExecutor command = commandMap.get(subcommand);
            command.executeCommand(p, args);
        } else {
            helpHandler(p, s, 0);
        }

        return true;

    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command cmd, String alias, String[] args)
    {
        Player player = (Player) commandSender;

        String subcommand = "-1";

        if(args.length == 1)
        {
            subcommand = args[0].toLowerCase();
        }

        final List<String> completions = new ArrayList<>();

        StringUtil.copyPartialMatches(
                args[0],
                commandMap.keySet(),
                completions);

        return completions;
    }
    


    public void helpHandler(Player p, String command, int page) {
        p.sendMessage("§dHelp for §f" + command + " §f| §rPage [" + (page + 1) + "/" +  helpMap.size() + "]");

        if (helpMap.containsKey(page)) {
            for(String s : helpMap.get(page)) {
                p.sendMessage(s.replace("{cmd}", command));
            }
        } else {
            p.sendMessage("Invalid page.");
            helpHandler(p, command, 0);
        }
    }

}
