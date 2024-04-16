package me.thomaszoord.mysticcube.listeners.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.thomaszoord.mysticcube.player.objects.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.utils.IntUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardLobby {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static void createScoreboard(Player p){
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);
        FastBoard board = new FastBoard(prisonPlayer.getPlayer());

        double percentage = ((double) prisonPlayer.getPoints() / prisonPlayer.getGoal()) * 100;

        DecimalFormat df = new DecimalFormat("0.#");
        String progressText = IntUtils.intToBar('▌', prisonPlayer.getPoints(), prisonPlayer.getGoal()) + " §7" + df.format(percentage) + "%";

        board.updateTitle("§5§lPRISON");
        board.updateLines(
                "§7       " + Bukkit.getOnlinePlayers().size() +" players",
                "",
                "§fRank: §d§l" + prisonPlayer.getTier(),
                "§fPoints: §7" + prisonPlayer.getPoints() + "/" + prisonPlayer.getGoal(),
                 progressText,
                "",
                "§fCoins: §a$" +IntUtils.formatNumberToK((int) prisonPlayer.getCoins()),
                "§fTokens: §e❈" + IntUtils.formatNumberToK((int) prisonPlayer.getTokens()),
                "§fGems: §d❖" + IntUtils.formatNumberToK(prisonPlayer.getGems()),
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
    public static void updateTokens(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(7,"Tokens: §d" + prisonPlayer.getTokens());
    }

    public static void updateCoins(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(6,"Coins: §d" + prisonPlayer.getCoins());
    }

    public static void updatePoints(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(3,"§fPoints: §7" + prisonPlayer.getPoints() + "/" + prisonPlayer.getGoal());
    }


    public static void updateScoreboard(PrisonPlayer prisonPlayer){
        FastBoard board = boards.get(prisonPlayer.getUuid());


        double percentage = (prisonPlayer.getPoints() / prisonPlayer.getGoal()) * 100;

        DecimalFormat df = new DecimalFormat("0.#");
        String progressText = IntUtils.intToBar('▌', prisonPlayer.getPoints(), prisonPlayer.getGoal()) + " §7" + df.format(percentage) + "%";


        board.updateLines(
                "§7     " + Bukkit.getOnlinePlayers().size() +" players",
                "",
                "§fRank: §d§l" + prisonPlayer.getTier(),
                "§fPoints: §7" + (int) prisonPlayer.getPoints() + "/" + (int) prisonPlayer.getGoal(),
                  progressText,
                "",
                "§fCoins: §a$" + IntUtils.formatNumberToK((int) prisonPlayer.getCoins()),
                "§fTokens: §e❈" + IntUtils.formatNumberToK((int) prisonPlayer.getTokens()),
                "§fGems: §d❖" + IntUtils.formatNumberToK(prisonPlayer.getGems()),
                "",
                "§7store.mysticcube.net"
        );
    }
}
