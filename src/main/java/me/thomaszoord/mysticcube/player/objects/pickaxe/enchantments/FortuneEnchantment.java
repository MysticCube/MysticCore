package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;

public class FortuneEnchantment extends PickaxeEnchantment {
    public FortuneEnchantment() {
        super("Fortune", 0);
    }

    @Override
    public void onBreakBlockEvent(PrisonPlayer prisonPlayer) {

//        int baseMultiplier = 1;
//        int coins = level * baseMultiplier;
//
//        prisonPlayer.setCoins(prisonPlayer.getCoins() + coins);
    }
}
