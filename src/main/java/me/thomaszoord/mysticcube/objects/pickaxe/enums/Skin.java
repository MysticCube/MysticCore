package me.thomaszoord.mysticcube.objects.pickaxe.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Skin {

    DEFAULT("Wood Pickaxe", Material.WOOD_PICKAXE),
    GOLD("Golden Pickaxe", Material.GOLD_PICKAXE),
    STONE("Stone Pickaxe", Material.STONE_PICKAXE),
    IRON("Iron Pickaxe", Material.IRON_PICKAXE),
    DIAMOND("Diamond Pickaxe",Material.DIAMOND_PICKAXE);

    private final String skinName;
    private final Material skinMaterial;

    Skin(String skinName, Material skinMaterial) {
        this.skinName = skinName;
        this.skinMaterial = skinMaterial;
    }



    public Material getSkinMaterial() {
        return skinMaterial;
    }

    public String getSkinName() {
        return skinName;
    }
}
