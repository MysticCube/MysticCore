package me.thomaszoord.mysticcube.player.ranks;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class RankUpEvent implements Cancellable {

    public void rankUpEvent(Player p){
        PrisonPlayer player = PrisonPlayerManager.getPrisonPlayer(p);





    }


    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
