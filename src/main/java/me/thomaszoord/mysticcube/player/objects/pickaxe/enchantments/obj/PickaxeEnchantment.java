package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.mine.mineblock.MineBlock;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PickaxeEnchantment {
    private String name;
    private int level;
    private final int maxLevel;
    private int price;
    private double activationPercentage;
    private int amountSpent = 0;
    private final int multiplierPrice;
    private final ItemStack guiItem;

    private final String[] description;


    public PickaxeEnchantment(String name, ItemStack guiItem, int level, int maxLevel, int price, double activationPercentage, int multiplierPrice, String... description) {
        this.name = name;
        this.guiItem = guiItem;
        this.level = level;
        this.maxLevel = maxLevel;
        this.price = price;
        this.activationPercentage = activationPercentage;
        this.multiplierPrice = multiplierPrice;
        this.description = description;
    }

    public abstract void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer);

    //Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getActivationPercentage() {
        return activationPercentage;
    }

    public void setActivationPercentage(int activationPercentage) {
        this.activationPercentage = activationPercentage;
    }

    public int getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(int amountSpent) {
        this.amountSpent = amountSpent;
    }

    public int getMultiplierPrice() {
        return multiplierPrice;
    }



    public GuiItem getGuiItem() {


        ArrayList<String> lore = new ArrayList<>(Arrays.asList(description));
        lore.add("");
        lore.add("§8Information");
        lore.add("§8│ §fLevel Progress: §7" + getLevel() + "§8/" + getMaxLevel());
        lore.add("§8│ §fActivation chance: §7[" + getActivationPercentage() + "]");
        lore.add("§8│ §fAmount spent so far: §e❈" + getAmountSpent());
        lore.add("");
        lore.add("§7Click to upgrade!");


        return ItemBuilder.from(guiItem)
                .name(Component.text("§a" + getName()))
                .setLore(lore)
                .asGuiItem(e -> {
                    PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer((Player) e.getWhoClicked());
                });
    }
}
