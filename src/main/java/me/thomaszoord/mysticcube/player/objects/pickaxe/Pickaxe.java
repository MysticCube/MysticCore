package me.thomaszoord.mysticcube.player.objects.pickaxe;

import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.APickaxeEnchantment;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.FortuneEnchantment;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enums.Skin;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Pickaxe {

    private Skin pickaxeSkin;
    private ArrayList<Skin> skinList;
    private int minedBlocks;


    //ENCHANTMENTS

    private FortuneEnchantment fortuneEnchantment;


    public Pickaxe(Skin pickaxeSkin, ArrayList<APickaxeEnchantment> enchantments, ArrayList<Skin> skinList) {
        this.pickaxeSkin = pickaxeSkin;
        this.skinList = skinList;
    }

    public Pickaxe() {
        this.pickaxeSkin = Skin.DEFAULT;
        this.skinList = new ArrayList<>();

        fortuneEnchantment = new FortuneEnchantment();
    }

    public ItemStack getPickaxeItemStack(){
        ItemStack pickaxe = new ItemStack(pickaxeSkin.getSkinMaterial());
        ItemMeta pickaxeItemMeta = pickaxe.getItemMeta();
        pickaxeItemMeta.setDisplayName("§aYour Pickaxe");



        pickaxeItemMeta.setLore(pickaxeLore());
        pickaxeItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pickaxeItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxeItemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        pickaxeItemMeta.spigot().setUnbreakable(true);
        pickaxeItemMeta.addEnchant(Enchantment.DIG_SPEED, 99, true);
        pickaxe.setItemMeta(pickaxeItemMeta);

        return pickaxe;
    }

    @NotNull
    private ArrayList<String> pickaxeLore() {
        ArrayList<String> pickaxeLore = new ArrayList<>();
        pickaxeLore.add("§7Information below");
        pickaxeLore.add("");
        pickaxeLore.add("§8Enchantments");
        pickaxeLore.add("§8▎ §f" + fortuneEnchantment.getName() + ": §7" + fortuneEnchantment.getLevel());
        pickaxeLore.add("");
        pickaxeLore.add("§8Statistics");
        pickaxeLore.add("");
        pickaxeLore.add("§8▎ §fMined Blocks: §7" + minedBlocks);
        pickaxeLore.add("§8▎ §fSkin: §7" + pickaxeSkin.getSkinName());
        pickaxeLore.add("");
        pickaxeLore.add("§aClick to see more!");

        return pickaxeLore;
    }


    public Skin getPickaxeSkin() {
        return pickaxeSkin;
    }

    public void setPickaxeSkin(Skin pickaxeSkin) {
        this.pickaxeSkin = pickaxeSkin;
    }

    public ArrayList<Skin> getSkinList() {
        return skinList;
    }


    public void setSkinList(ArrayList<Skin> skinList) {
        this.skinList = skinList;
    }

    public int getMinedBlocks() {
        return minedBlocks;
    }

    public void setMinedBlocks(int minedBlocks) {
        this.minedBlocks = minedBlocks;
    }
}
