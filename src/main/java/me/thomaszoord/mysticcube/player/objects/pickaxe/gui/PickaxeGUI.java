package me.thomaszoord.mysticcube.player.objects.pickaxe.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.Pickaxe;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Fortune;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.PointBuster;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.TokenCollector;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Velocity;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.EnchantmentsManager;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PickaxeGUI {

    public static void openPickaxeGUI(Player p){

        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);
        Pickaxe pickaxe = prisonPlayer.getPickaxe();

        Gui gui = Gui.gui().rows(6).title(Component.text("Your Pickaxe")).create();
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });

        gui.setItem(1, 4, yourPickaxe(p, pickaxe));
        gui.setItem(1, 6, yourEnchantments(p, pickaxe));


        try{
            gui.setItem(3, 3, pickaxe.getGuiEnchantmentItem(Fortune.class));
            gui.setItem(3, 4, pickaxe.getGuiEnchantmentItem(Velocity.class));
            gui.setItem(3, 5, pickaxe.getGuiEnchantmentItem(TokenCollector.class));
            gui.setItem(3, 6, pickaxe.getGuiEnchantmentItem(PointBuster.class));
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }



        gui.open(p);


    }




    public static void openSkinsGUI(Player p){

        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);
        Pickaxe pickaxe = prisonPlayer.getPickaxe();

        Gui gui = Gui.gui().rows(5).title(Component.text("Your Pickaxe")).create();

    }



    //GUI ITEMS
    @SuppressWarnings("Deprecated")
    public static GuiItem yourPickaxe(Player p, Pickaxe pickaxe){

        return ItemBuilder.from(pickaxe.getPickaxeItemStack())
                .setLore("§7Click to see the skins your pickaxe",
                        "§7can have to boost your mining!", "",
                        "§8│ §fSelected: §7" + pickaxe.getPickaxeSkin().getSkinName(),
                        "§8│ §fUnlocked: §7" + pickaxe.getSkinList().size() + "§8/10")

                .setName("§aPickaxe Skins")
                .asGuiItem( e -> {
                            openSkinsGUI(p);
                        }
                );
    }
    public static GuiItem yourEnchantments(Player p, Pickaxe pickaxe){

        return ItemBuilder.from(Material.ENCHANTED_BOOK)
                .setLore("§7Click to see the enchantments your",
                        "§7pickaxe can have to boost your mining!", "",
                        "§8│ §fUnlocked: §7" + pickaxe.getEnchantments().size() + "/" + EnchantmentsManager.enchantments.size())

                .setName("§aEnchantments")
                .flags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                .enchant(Enchantment.DIG_SPEED)
                .asGuiItem( e -> {
                            openPickaxeGUI(p);
                        }
                );
    }


}
