package me.thomaszoord.mysticcube.objects.pickaxe.shop;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.fortune.Fortune;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.objects.pickaxe.shop.enums.PurchaseType;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnchantmentShop {
    public static PurchaseType checkPurchase(PrisonPlayer prisonPlayer, PickaxeEnchantment enchantment, int level) {
        if (enchantment.getLevel() >= enchantment.getMaxLevel()) {
            return PurchaseType.LIMIT_LEVEL;
        }

        else if (prisonPlayer.getTokens() >= calculateTotalCost(enchantment, level)) {
            return PurchaseType.SUCCESS;
        }

        return PurchaseType.INSUFFICIENT_FUNDS;
    }


    public static void buyEnchantment(PrisonPlayer prisonPlayer, PickaxeEnchantment enchantment, int level){
        int totalCost = calculateTotalCost(enchantment, level);

        Player p = prisonPlayer.getPlayer();

        switch (checkPurchase(prisonPlayer, enchantment, level)){
            case SUCCESS:
                p.sendMessage("§a§lGG! §aYour purchased §7" + level + " level's of §e" + enchantment.getName());
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);

                prisonPlayer.setTokens(prisonPlayer.getTokens() - totalCost);
                enchantment.setLevel(enchantment.getLevel() + level);
                enchantment.setAmountSpent(enchantment.getAmountSpent() + totalCost);
                enchantment.setPrice(enchantment.getPrice() + enchantment.getMultiplierPrice());

                p.getInventory().setItem(0, prisonPlayer.getPickaxe().getPickaxeItemStack());

                break;

            case LIMIT_LEVEL:
                p.sendMessage("§cYou can't buy that!");
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.5F, 0.5F);
                break;

            case INSUFFICIENT_FUNDS:
                p.sendMessage("§cYou don't have sufficient funds!");
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 0.5F, 0.5F);

                break;
        }
    }


    public static int calculateTotalCost(PickaxeEnchantment enchantment, int level) {
        int totalCost = 0;
        int currentPrice = enchantment.getPrice();

        for (int i = 0; i < level; i++) {
            totalCost += currentPrice;
            currentPrice += enchantment.getMultiplierPrice();
        }

        return totalCost;
    }


    public static void desenchantEnchantment(PrisonPlayer p, PickaxeEnchantment enchantment){
        Gui gui = Gui.gui().title(Component.text("Disenchant")).rows(3).create();

        gui.setDefaultClickAction(e -> {
            e.setCancelled(true);
        });
        GuiItem confirm = ItemBuilder.from(new ItemStack(Material.STAINED_CLAY, 1, (short) 5))
                .setName("§aConfirm")
                .asGuiItem(e -> {
                      gui.close(p.getPlayer());
                      desenchantEnchantment(enchantment, p);
                        });

        GuiItem cancel = ItemBuilder.from(new ItemStack(Material.STAINED_CLAY, 1, (short) 14))
                .setName("§cCancel")
                .asGuiItem( e ->
                        gui.close(p.getPlayer()));


        gui.setItem(2, 4, confirm);
        gui.setItem(2, 6, cancel);

        gui.open(p.getPlayer());

    }


    public static void desenchantEnchantment(PickaxeEnchantment enchantment, PrisonPlayer p){
        if(enchantment instanceof Fortune){
            enchantment.setLevel(1);
        } else {
            enchantment.setLevel(0);
        }


        p.setTokens(p.getTokens() + enchantment.getAmountSpent());
        p.getPlayer().sendMessage("Desencantou kk");
        p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
    }


}
