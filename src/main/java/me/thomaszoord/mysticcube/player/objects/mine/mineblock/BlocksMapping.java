package me.thomaszoord.mysticcube.player.objects.mine.mineblock;

import me.thomaszoord.mysticcube.Core;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineLevel;
import me.thomaszoord.mysticcube.utils.Configs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlocksMapping {



    public static MineLevel findMineLevelForPlayer(PrisonPlayer p){

        boolean levelFinded = false;

        for(MineLevel mineLevel : Core.mineLevels){
            if(mineLevel.isInRange(p.getTier())){
                levelFinded = true;
                return mineLevel;
            }
        }

        if(!levelFinded){
            System.out.println("The player "+ p.getPlayer().getName() + " level is too high to find the MineLevel");
        }

        return null;

    }

    public static List<MineLevel> getBlocksList() {
        List<MineLevel> mineLevelList = new ArrayList<>();

        if (Configs.config.getYamlConfiguration().contains("Mine")) {
            ConfigurationSection mineSection = Configs.config.getYamlConfiguration().getConfigurationSection("Mine");

            for (String rankKey : mineSection.getKeys(false)) {
                ConfigurationSection rankSection = mineSection.getConfigurationSection(rankKey);

                List<Integer> mineLevels = rankSection.getIntegerList("Levels");
                int minLevel = mineLevels.get(0);
                int maxLevel = mineLevels.get(1);

                MineLevel mineLevel = new MineLevel(minLevel, maxLevel);

                ConfigurationSection blocksSection = rankSection.getConfigurationSection("Blocks");

                if (blocksSection != null) {
                    for (String blockKey : blocksSection.getKeys(false)) {
                        ConfigurationSection blockSection = blocksSection.getConfigurationSection(blockKey);

                        String id = blockSection.getString("BLOCK_ID");
                        double points = blockSection.getDouble("POINTS");
                        double coins = blockSection.getDouble("COINS");
                        double tokens = blockSection.getDouble("TOKENS");

                        short data = 0;
                        if (id.contains(":")) {
                            String[] idParts = id.split(":");
                            id = idParts[0];
                            data = Short.parseShort(idParts[1]);
                        }

                        Material material = Material.matchMaterial(id);
                        if (material == null) {
                            Bukkit.getConsoleSender().sendMessage("Unknown material: " + id);
                            continue;
                        }


                        MineBlock mineBlock = new MineBlock(material, data, points, coins, tokens);

                        Bukkit.getConsoleSender().sendMessage("§eRegistred block! §7 | §dBlock: §e" +
                                mineBlock.getMaterial().name() + " §8| §fPoints: §d" + mineBlock.getPoints() + " §8| §fTokens: §d"+ mineBlock.getTokens() + " §d| §fCoins: §d" + mineBlock.getCoins() + " |");


                        mineLevel.addBlock(mineBlock);


                    }
                } else {
                    Bukkit.getConsoleSender().sendMessage("Not founded!");
                }

                mineLevelList.add(mineLevel);
            }
        } else {
            Bukkit.getConsoleSender().sendMessage("§cERROR! 'Mine' section not found in config!");
        }

        return mineLevelList;
    }

//    public static List<MineLevel> getBlocksList() {
//        List<MineLevel> mineLevelBlocksList = new ArrayList<>();
//
//        Object mineLevelsObj = Configs.config.getYamlConfiguration().get("Mine");
//
//        if (mineLevelsObj instanceof List) {
//            List<Map<String, Object>> mineLevels = (List<Map<String, Object>>) mineLevelsObj;
//
//            for (Map<String, Object> level : mineLevels) {
//                List<Integer> levelRange = (List<Integer>) level.get("Mine Level");
//
//                int minLevel = levelRange.get(0);
//                int maxLevel = levelRange.get(1);
//
//                MineLevel mineLevelBlocks = new MineLevel(minLevel, maxLevel);
//
//
//                List<Map<String, Object>> blocks = (List<Map<String, Object>>) level.get("Blocks");
//
//                for (Map<String, Object> block : blocks) {
//                    String id = (String) block.get("BLOCK");
//                    int data = (int) block.get("data");
//                    double points = (double) block.get("points");
//                    double coins = (double) block.get("coins");
//                    double tokens = (double) block.get("tokens");
//
//                    Material material = Material.matchMaterial(id);
//
//                    if (material == null) {
//                        System.err.println("Unknown material: " + id);
//                        continue;
//                    }
//
//                    MineBlock mineBlock;
//                    if (data != 0) {
//                        mineBlock = new MineBlock(material, (short) data, points, coins, tokens);
//                    } else {
//                        mineBlock = new MineBlock(material, points, coins, tokens);
//                    }
//
//                    Bukkit.getConsoleSender().sendMessage(" ");
//                    Bukkit.getConsoleSender().sendMessage("Registered block!");
//                    Bukkit.getConsoleSender().sendMessage("Level: " + mineLevelBlocks.minLevel + " - " + mineLevelBlocks.maxLevel);
//                    Bukkit.getConsoleSender().sendMessage("Block: " + mineBlock.getMaterial());
//                    Bukkit.getConsoleSender().sendMessage("Coins: " + mineBlock.getCoins());
//                    Bukkit.getConsoleSender().sendMessage("Points: " + mineBlock.getPoints());
//                    Bukkit.getConsoleSender().sendMessage(" " );
//
//                    mineLevelBlocks.addBlock(mineBlock);
//                }
//
//                mineLevelBlocksList.add(mineLevelBlocks);
//            }
//        } else {
//            System.err.println("Error: 'mine_levels' is not a list.");
//        }
//
//        return mineLevelBlocksList;
//    }


}
