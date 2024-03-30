package me.thomaszoord.mysticcube.listeners.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.utils.IntUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardLobby {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static void createScoreboard(Player p){
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);
        FastBoard board = new FastBoard(prisonPlayer.getPlayer());

        board.updateTitle("§5§lPRISON");
        board.updateLines(
                "§7       " + Bukkit.getOnlinePlayers().size() +" players",
                "",
                "§fRank: §d§l" + IntUtils.intToRoman(prisonPlayer.getTier()),
                "§fPoints: §7" + prisonPlayer.getPoints() + "/",
                "§d▌§7▌▌▌▌▌▌▌▌▌▌▌ §75.2%",
                "",
                "§fCoins: §d" + prisonPlayer.getCoins(),
                "§fGems: §d" + prisonPlayer.getGems(),
                "§fCash: §d" + prisonPlayer.getCash(),
                "",
                "§7store.mysticcube.net"
        );


        boards.put(prisonPlayer.getUuid(), board);

    }

    public static void removeScoreboard(Player p){
        FastBoard board = ScoreboardLobby.boards.remove(p.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }
    public static void updateGems(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(7,"Gems: §d" + prisonPlayer.getGems());
    }

    public static void updateCoins(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(6,"Coins: §d" + prisonPlayer.getCoins());
    }

    public static void updatePoints(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(3,"§fPoints: §7" + prisonPlayer.getPoints() + "/");
    }


    public static void updateScoreboard(PrisonPlayer prisonPlayer){
        FastBoard board = boards.get(prisonPlayer.getUuid());

        board.updateLines(
                "&7     " + Bukkit.getOnlinePlayers().size() +" players",
                "",
                "§fRank: §d§l" + prisonPlayer.getTier(),
                "§fPoints: §7" + prisonPlayer.getPoints(),
                "§d▌§7▌▌▌▌▌▌▌▌▌▌▌ §75.2%",
                "",
                "§fCoins: §d" + prisonPlayer.getCoins(),
                "§fGems: §d" + prisonPlayer.getGems(),
                "§fCash: §d" + prisonPlayer.getCash(),
                "",
                "§7store.mysticcube.net"
        );
    }
}
