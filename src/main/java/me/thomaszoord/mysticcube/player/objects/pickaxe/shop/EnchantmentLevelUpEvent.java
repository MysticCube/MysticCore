package me.thomaszoord.mysticcube.player.objects.pickaxe.shop;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments.obj.PickaxeEnchantment;
import me.thomaszoord.mysticcube.player.objects.pickaxe.shop.enums.PurchaseType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EnchantmentLevelUpEvent {
    private static PurchaseType checkPurchase(PrisonPlayer prisonPlayer, PickaxeEnchantment enchantment, int level) {
        if (enchantment.getLevel() >= enchantment.getMaxLevel()) {
            return PurchaseType.LIMIT_LEVEL;
        }

        if (prisonPlayer.getTokens() >= calculateTotalCost(enchantment, level)) {
            return PurchaseType.SUCCESS;
        }

        return PurchaseType.INSUFFICIENT_FUNDS;
    }


    public static void buyEnchantmentEvent(PrisonPlayer prisonPlayer, PickaxeEnchantment enchantment, int level){
        int totalCost = calculateTotalCost(enchantment, level);

        Player p = prisonPlayer.getPlayer();

        switch (checkPurchase(prisonPlayer, enchantment, level)){
            case SUCCESS:
                p.sendMessage("§a§lGG! §aYour purchased §7" + level + " level's of §e" + enchantment.getName());
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);

                prisonPlayer.setTokens(prisonPlayer.getTokens() - totalCost);
                enchantment.setLevel(enchantment.getLevel() + level);
                enchantment.setAmountSpent(enchantment.getAmountSpent() + totalCost);

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


    private static int calculateTotalCost(PickaxeEnchantment enchantment, int level) {
        int totalCost = 0;
        int currentPrice = enchantment.getPrice();

        for (int i = 0; i < level; i++) {
            totalCost += currentPrice;
            currentPrice += enchantment.getMultiplierPrice();
        }

        return totalCost;
    }


}
