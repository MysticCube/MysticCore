package me.thomaszoord.mysticcube.listeners.npcs.guis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.utils.IntUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;

public class MinerGUI {

    public static void mineGUI(PrisonPlayer p){

        Gui mineGui = Gui.gui().
                rows(3).title(Component.text("/mine")).create();

        String name = "§aYour statistics";

        mineGui.setDefaultClickAction(e ->{
            e.setCancelled(true);
        });



        GuiItem head = ItemBuilder.skull()
                .owner(Bukkit.getOfflinePlayer(p.getUuid()))
                .setName(name)
                .setLore(statistics(p))
                .asGuiItem(e -> {

                });

        GuiItem pickaxe = ItemBuilder.from(Material.WOOD_PICKAXE)
                .setName("§aYour Pickaxe")
                .setLore(pickaxe())
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_PLACED_ON)
                .asGuiItem(e -> {

                });


        GuiItem mine = ItemBuilder.skull()
                .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDA1Mzg1MTUyN2M0YzllZjMwYTYxZmIwNjdlYmNlOTU3YzcyNmUxNjg3ZjhiNTMwZmI0YTZiZWViYTQzOGJkIn19fQ==")
                .setName("§aYour Mine §8[Rank 15]")
                .setLore(mine(p))
                .asGuiItem(e -> {


                  PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer((Player) e.getWhoClicked());
                  prisonPlayer.getMine().teleportToMine(prisonPlayer.getPlayer());


                });


        GuiItem explosives = ItemBuilder.skull()
                .texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FmNTk3NzZmMmYwMzQxMmM3YjU5NDdhNjNhMGNmMjgzZDUxZmU2NWFjNmRmN2YyZjg4MmUwODM0NDU2NWU5In19fQ==")
                .setName("§cExplosives..")
                .setLore(explosives(p))
                .color(Color.GRAY)
                .asGuiItem(e -> {



                });


        GuiItem arrow = ItemBuilder.from(Material.ARROW)
                .setName("§7Back")
                .setLore(arrow(p))
                .asGuiItem(e -> {
                    p.getPlayer().closeInventory();
                });


        mineGui.setItem(2, 3, head);
        mineGui.setItem(2, 5, pickaxe);
        mineGui.setItem(2, 6, explosives);
        mineGui.setItem(2, 7, mine);
        mineGui.setItem(3, 1, arrow);

        mineGui.open(p.getPlayer());

    }


    public static ArrayList<String> statistics(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Here you can see your statistics");
        lore.add("§7about your mining on the server");
        lore.add("");
        lore.add("§8│ §fMined blocks: §7" + IntUtils.formatNumberToK(p.getPickaxe().getMinedBlocks()) + " §8(" + p.getPickaxe().getMinedBlocks() + ")");
        lore.add("§8│ §fEnchantments: §70§8/10");
        lore.add("§8│ §fYour mine: §7[§a■§7■■■■■] (§a0.0%§7)");
        lore.add("");

        return lore;
    }
    public static ArrayList<String> pickaxe(){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Click to see all the information");
        lore.add("§7about your pickaxe.");


        return lore;
    }
    public static ArrayList<String> explosives(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Acquire explosives and boost your");
        lore.add("§7mining in our prison, just click!");


        return lore;
    }
    public static ArrayList<String> mine(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Click to go to your mine!");

        return lore;
    }

    public static ArrayList<String> arrow(PrisonPlayer p){

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Go back.");

        return lore;
    }

}
