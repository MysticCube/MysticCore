package me.thomaszoord.mysticcube.player.objects.pickaxe.enchantments;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.objects.pickaxe.Pickaxe;

public class FortuneEnchantment extends APickaxeEnchantment{
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
