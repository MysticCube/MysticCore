package me.thomaszoord.mysticcube.player;

import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PrisonPlayerManager {

    public static HashMap<Player, PrisonPlayer> prisonPlayerList = new HashMap<>();

    public static PrisonPlayer getPrisonPlayer(Player player){
        return prisonPlayerList.get(player);
    }

    public static void addPrisonPlayer(Player p, PrisonPlayer prisonPlayer) {
        prisonPlayerList.put(p, prisonPlayer);
    }

    public static void removePrisonPlayer(PrisonPlayer p){
        prisonPlayerList.remove(p);
    }
}
