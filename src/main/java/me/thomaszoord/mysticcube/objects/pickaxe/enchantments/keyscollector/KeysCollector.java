package me.thomaszoord.mysticcube.objects.pickaxe.enchantments.keyscollector;

import dev.triumphteam.gui.guis.Gui;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.objects.pickaxe.enchantments.PickaxeEnchantment;
import me.thomaszoord.mysticcube.utils.StringUtils;
import me.thomaszoord.mysticcube.utils.packets.ActionbarAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class KeysCollector extends PickaxeEnchantment {


    public KeysCollector() {
        super(KeysCollectorConfig.name,
                StringUtils.getItemStackByString(KeysCollectorConfig.guiItem),
                KeysCollectorConfig.minimumLevel,
                0,
                KeysCollectorConfig.maxLevel,
                KeysCollectorConfig.price,
                KeysCollectorConfig.baseActivationPercentage,
                KeysCollectorConfig.multiplierPrice,
                Arrays.asList(KeysCollectorConfig.description),
                KeysCollectorConfig.color);
    }

    @Override
    public void enchantmentBreakBlockEvent(PrisonPlayer prisonPlayer, Location mineBlock) {
        if(shouldActivate()){
            prisonPlayer.getPlayer().getInventory().addItem(new ItemStack(Material.STONE, 1));

            ActionbarAPI.sendActionBarMessage(prisonPlayer.getPlayer(), "§fYou earned a Key! Use §d/blablabla §fto see your achievements!");
        }
    }



    @Override
    public Gui shopGUI(PrisonPlayer prisonPlayer) {
        return null;
    }
}
