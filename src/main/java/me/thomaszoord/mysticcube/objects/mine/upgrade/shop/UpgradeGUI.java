package me.thomaszoord.mysticcube.objects.mine.upgrade.shop;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.thomaszoord.mysticcube.objects.mine.upgrade.shop.enums.UpgradeShop;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import net.kyori.adventure.text.Component;

import java.util.Arrays;

public class UpgradeGUI {

    public void openUpgradeGUI(PrisonPlayer p){
        Gui gui = Gui.gui().title(Component.text("/upgrades")).rows(3).create();

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        GuiItem mineSize = ItemBuilder.skull().texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGUwOTViNmUwOWY2MWZkNGJiNWM0ZmYyYzRhMGE3Zjk2NGYxY2U5NWZjMzljMGNmOTQyMjU2ZmI3YWJjMzY1MCJ9fX0=")
                .setName("§aMine Size")
                .setLore(Arrays.asList(
                    "§7Click to increase your mine size",
                        "§7without having to wait to rank up!",
                        "",
                        "§8| §fPrice:" + "§a$" + UpgradeShop.baseUpgradePrice,
                        "§8| §fLevel: §8§m1 §8▸ §72",
                        "§8| §fSize: §8§m"
                                + p.getMine().getMineRank().getMineSize()
                                + "x" + p.getMine().getMineRank().getMineSize()
                                +  "§8▸ §7" + UpgradeShop.returnNextRank(p).getMineSize() + "x" + UpgradeShop.returnNextRank(p).getMineSize(),
                        "",
                        "§7Click to purchase!"

                )).asGuiItem(e ->{
                    UpgradeShop.upgradeMine(p);

                });
    }
}
