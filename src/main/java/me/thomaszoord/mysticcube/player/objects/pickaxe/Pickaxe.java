package me.thomaszoord.mysticcube.player.objects.pickaxe;

import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Fortune;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.PointBuster;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.TokenCollector;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.Velocity;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
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
    private ArrayList<PickaxeEnchantment> enchantments;



    public PickaxeEnchantment getEnchantment(Class<? extends PickaxeEnchantment> enchantmentClass) {
        return enchantments.stream()
                .filter(enchantment -> enchantment.getClass().equals(enchantmentClass))
                .findFirst()
                .orElse(null);
    }



    public Pickaxe(Skin pickaxeSkin, ArrayList<PickaxeEnchantment> enchantments, ArrayList<Skin> skinList) {
        this.pickaxeSkin = pickaxeSkin;
        this.skinList = skinList;
        this.enchantments = enchantments;

    }

    public Pickaxe() {
        this.pickaxeSkin = Skin.IRON;
        this.skinList = new ArrayList<>();

        this.enchantments = new ArrayList<>(); {
            enchantments.add(new Fortune());
            enchantments.add(new Velocity());
            enchantments.add(new TokenCollector());
            enchantments.add(new PointBuster());
        };


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
        for(PickaxeEnchantment pickaxeEnchantment : enchantments){
            pickaxeLore.add("§8▎ §f" + pickaxeEnchantment.getName() + ": §7" + pickaxeEnchantment.getLevel());
        }
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

    public ArrayList<PickaxeEnchantment> getEnchantments() {
        return enchantments;
    }
}
