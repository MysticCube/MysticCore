package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.fortune;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.listeners.packets.BlockBreakPacket;
import me.thomaszoord.mysticcube.objects.pickaxe.gui.PickaxeGUI;
import me.thomaszoord.mysticcube.objects.pickaxe.shop.EnchantmentShop;
import me.thomaszoord.mysticcube.objects.pickaxe.shop.enums.PurchaseType;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class Fortune extends PickaxeEnchantment {


    public Fortune() {
        super(FortuneConfig.name,
                StringUtils.getItemStackByString(FortuneConfig.guiItem),
                FortuneConfig.minimumLevel,
                1,
                FortuneConfig.maxLevel,
                FortuneConfig.price,
                FortuneConfig.baseActivationPercentage,
                FortuneConfig.multiplierPrice,
                Arrays.asList(FortuneConfig.description),
                FortuneConfig.color);
    }


    private final double coinMultiplier = FortuneConfig.coinMultiplier;
    private double buffCoins(){
        double buffCoin = coinMultiplier;
        return buffCoin * getLevel();
    }


    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock) {
        BlockBreakPacket.coins += buffCoins();
    }

    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        Gui gui = Gui.gui().title(Component.text(getName()))
                .rows(4).create();

        gui.setDefaultClickAction(e -> e.setCancelled(true));

        gui.setItem(2, 3, levelUpGuiItem(gui, 13, prisonPlayer));
        gui.setItem(2, 4, levelUpGuiItem(gui, 1, prisonPlayer));
        gui.setItem(2, 5, levelUpGuiItem(gui, 5, prisonPlayer));
        gui.setItem(2, 6, levelUpGuiItem(gui, 100, prisonPlayer));
        gui.setItem(2, 7, levelUpGuiItem(gui, 1000, prisonPlayer));

        GuiItem arrow = ItemBuilder.from(Material.ARROW)
                .setName("§7Go back.").asGuiItem(e -> {
                    PickaxeGUI.openPickaxeGUI(prisonPlayer.getPlayer());
                });
        GuiItem disenchantItem = ItemBuilder.from(Material.ANVIL)
                .setName("§cDisenchant")
                .setLore(Arrays.asList(
                        "§7Disenchanting is a great way to remove an",
                        "§7unwanted enchantment while receiving §f50%",
                        "§7of what you've already spent.",
                        "",
                        "§8│ §fRefund amount: §e❈237.4k",
                        "",
                        "§7Click to disenchant!"
                ))
                .asGuiItem(e -> {
                EnchantmentShop.desenchantEnchantment(prisonPlayer, this);
                });

        gui.setItem(4, 5, disenchantItem);
        gui.setItem(4, 1, arrow);

        return gui;
    }


    public GuiItem levelUpGuiItem(Gui gui, int level, PrisonPlayer prisonPlayer) {

        String name;
        String color;
        short paneColor;

        if(EnchantmentShop.checkPurchase(prisonPlayer, this, level).equals(PurchaseType.SUCCESS)){
            name = "§a+" + level + " Levels";
            color = "§e";
            paneColor = 5;
        } else {
            name = "§c+" + level + " Levels";
            color = "§c";
            paneColor = 7;
        }

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Information below");
        lore.add(" ");
        lore.add("§8 │ §fPrice: " + color + "❈" + EnchantmentShop.calculateTotalCost(this, level));
        lore.add("§8 │ §fLevel: §8§m" + getLevel() + " §7▸ " + (getLevel() + level));
        lore.add("§8 │ §fGain per mined block: §8§m$" + buffCoins() + " §7▸ " + color + "$" + (coinMultiplier * (getLevel() + 1)));
        lore.add(" ");
        lore.add("§7Click to purchase!");

        return ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, paneColor))
                .name(Component.text(name))
                .setLore(lore)
                .asGuiItem(e -> {
                    e.setCancelled(true);
                    EnchantmentShop.buyEnchantment(prisonPlayer, this, level);

                    gui.updateItem(e.getSlot(), levelUpGuiItem(gui, level, prisonPlayer));;
                    gui.update();
                });
    }

}
