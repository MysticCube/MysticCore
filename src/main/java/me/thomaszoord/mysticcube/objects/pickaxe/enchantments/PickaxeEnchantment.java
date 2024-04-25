package me.thomaszoord.mysticcube.objects.pickaxe.enchantments;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class PickaxeEnchantment {

    private String name = "DefaultEnchantment";
    private int level = 1;
    private int minimumLevel = 5;
    private String color = "&6";
    private int maxLevel = 5;

    private int price = 1000;

    private double activationPercentage = 0.110;

    private int amountSpent = 0;

    private int multiplierPrice = 20;

    private ItemStack guiItem = new ItemStack(Material.STAINED_CLAY, 1, (short) 55);

    private List<String> description;


    public PickaxeEnchantment(String name, ItemStack guiItem, int minimumLevel, int level, int maxLevel, int price, double activationPercentage, int multiplierPrice, List<String> description, String color) {
        this.name = name;
        this.guiItem = guiItem;
        this.minimumLevel = minimumLevel;
        this.level = level;
        this.maxLevel = maxLevel;
        this.price = price;
        this.activationPercentage = activationPercentage;
        this.multiplierPrice = multiplierPrice;
        this.description = description;
        this.color = color;
    }

    public void activateEnchantment(PrisonPlayer prisonPlayer, Location mineBlock){
        if(this.level == 0){
            return;
        }

        enchantmentBreakBlockEvent(prisonPlayer, mineBlock);
    }
    public abstract void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock);

    protected boolean shouldActivate() {
        Random random = new Random();
        double randomNumber = random.nextDouble() * 100;
        return randomNumber <= getActivationPercentage();
    }




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

    public void setActivationPercentage(double activationPercentage) {
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


        ArrayList<String> lore = new ArrayList<>(description);
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
                    shopGUI(prisonPlayer).open(prisonPlayer.getPlayer());
                });
    }



    public int getMinimumLevel() {
        return minimumLevel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setMinimumLevel(int minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setMultiplierPrice(int multiplierPrice) {
        this.multiplierPrice = multiplierPrice;
    }

    public void setGuiItem(ItemStack guiItem) {
        this.guiItem = guiItem;
    }

    public List<String> getDescription() {
        return description;
    }

    public abstract Gui shopGUI(PrisonPlayer prisonPlayer);
}
