package me.thomaszoord.mysticcube.player.ranks;

import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class RankUpEvent implements Cancellable {

    public void rankUpEvent(Player p){
        PrisonPlayer player = PrisonPlayerManager.getPrisonPlayer(p);

        //OLD ELO
        if(player.getRank().getLevel() > player.getRank().getLevel()){

        }

        //IF PLAYER IS ON MAX LEVEL OF HIS RANK, RANK UP

        //NEW ELO

    }


    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
