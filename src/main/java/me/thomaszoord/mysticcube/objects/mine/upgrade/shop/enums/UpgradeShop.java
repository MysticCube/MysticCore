package me.thomaszoord.mysticcube.objects.mine.upgrade.shop.enums;

import me.thomaszoord.mysticcube.objects.mine.MineRank;
import me.thomaszoord.mysticcube.objects.pickaxe.shop.enums.PurchaseType;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class UpgradeShop {

    public static double baseUpgradePrice = 1500.00;
    public static PurchaseType checkPurchase(PrisonPlayer prisonPlayer) {
        if (prisonPlayer.getCoins() >= baseUpgradePrice) {
            return PurchaseType.SUCCESS;
        }

        return PurchaseType.INSUFFICIENT_FUNDS;
    }

    public static void buyMineUpgrade(PrisonPlayer prisonPlayer){

        Player p =  prisonPlayer.getPlayer();

        switch (checkPurchase(prisonPlayer)){
            case SUCCESS:
                prisonPlayer.setCoins(prisonPlayer.getCoins() - baseUpgradePrice);
                baseUpgradePrice += (baseUpgradePrice / 2);
                upgradeMine(prisonPlayer);

                p.sendMessage(
                        "§a§lGG! §aYou have acquired §7Mining Size §alevel §f1§a!"
                );
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 0.5F);
                break;
            case INSUFFICIENT_FUNDS:
                p.sendMessage(
                        "§cYou don't have enough to acquire this!"
                );
                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 0.5F, 0.5F);
                break;

            default:
                p.sendMessage("§cThat's was not supposed to happen.. Contact a admin!");
                break;
        }
    }

    public static void upgradeMine(PrisonPlayer prisonPlayer){

        MineRank mineRank = prisonPlayer.getMine().getMineRank();
       if(mineRank.equals(MineRank.RANK_1)){
           prisonPlayer.getMine().setMineRank(MineRank.RANK_2);
       } else if (mineRank.equals(MineRank.RANK_2)) {
           prisonPlayer.getMine().setMineRank(MineRank.RANK_3);
       } else if(mineRank.equals(MineRank.RANK_3)){
           prisonPlayer.getPlayer().sendMessage("Sem nivel ainda amigao me perdoa");
       }
    }

    public static MineRank returnNextRank(PrisonPlayer prisonPlayer){

        MineRank mineRank = prisonPlayer.getMine().getMineRank();

        if(mineRank.equals(MineRank.RANK_1)){
            return MineRank.RANK_2;
        } else if (mineRank.equals(MineRank.RANK_2)) {
            return MineRank.RANK_3;

        }

         return null;
    }




    public static void setBaseUpgradePrice(double baseUpgradePrice) {
        UpgradeShop.baseUpgradePrice = baseUpgradePrice;
    }
}
