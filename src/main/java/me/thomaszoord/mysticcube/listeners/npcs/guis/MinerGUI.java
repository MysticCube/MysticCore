package me.thomaszoord.mysticcube.listeners.npcs.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.utils.ZUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;

public class MinerGUI {

    public static void mineGUI(PrisonPlayer p){

        Gui mineGui = Gui.gui().
                rows(3).title(Component.text("Miner")).create();

        String name = "§a§lYOUR STATISTICS";

        mineGui.setDefaultClickAction(e ->{
            e.setCancelled(true);
        });

        GuiItem head = ItemBuilder.from(Material.PLAYER_HEAD)
                .setSkullOwner(p.getPlayer())
                .setName(name)
                .setLore(statistics(p))
                .asGuiItem(e -> {

                });

        GuiItem pickaxe = ItemBuilder.from(Material.WOODEN_PICKAXE)
                .setSkullOwner(p.getPlayer())
                .setName("§a§lYOUR PICKAXE §8[Lvl. 1]")
                .setLore(pickaxe(p))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON)
                .asGuiItem(e -> {

                });


        GuiItem mine = ItemBuilder.skull()
                .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA1Mzg1MTUyN2M0YzllZjMwYTYxZmIwNjdlYmNlOTU3YzcyNmUxNjg3ZjhiNTMwZmI0YTZiZWViYTQzOGJkIn19fQ==")
                .setName("§a§lYOUR MINE §8[Lvl. 15]")
                .setLore(mine(p))
                .asGuiItem(e -> {



                });


        GuiItem comingsoon = ItemBuilder.from(Material.GRAY_DYE)
                .setSkullOwner(p.getPlayer())
                .setName("§7Coming soon..")
                .color(Color.GRAY)


                .asGuiItem(e -> {



                });



        mineGui.setItem(2, 3, head);
        mineGui.setItem(2, 5, pickaxe);
        mineGui.setItem(2, 6, mine);
        mineGui.setItem(2, 7, comingsoon);

        mineGui.open(p.getPlayer());

    }


    public static ArrayList<String> statistics(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Here you can see your statistics");
        lore.add("§7about your mining on the server");
        lore.add("");
        lore.add("§8· §fMined blocks: §7" + ZUtils.formatNumberToK(p.getBlocks()) + " §8(" + p.getBlocks() + ")");
        lore.add("§8· §7Enchantments: §70§8/10");
        lore.add("");
        lore.add("§8· §fYour Mine: §8[§a■■§7■■■■] §8(" + "§a0.00%" + "§8)" );
        lore.add("§8· §fYour Pickaxe: §8[§a■■■■§7■■] §8(" + "§a0.00%" + "§8)" );

        return lore;
    }
    public static ArrayList<String> pickaxe(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Click to see all the information");
        lore.add("§7about your pickaxe.");


        return lore;
    }
    public static ArrayList<String> mine(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Click to go to your mine.");

        return lore;
    }

}
