package me.thomaszoord.mysticcube.listeners.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import me.thomaszoord.mysticcube.player.PrisonPlayer;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Scoreboard {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static void createScoreboard(Player p){
        PrisonPlayer prisonPlayer = PrisonPlayerManager.getPrisonPlayer(p);
        FastBoard board = new FastBoard(prisonPlayer.getPlayer());

        board.updateTitle("§d§lMYSTIC CUBE");
        board.updateLines(
                "§822/02/2024 §714:29",
                "",
                "§d» §fMoney: §5" + prisonPlayer.getMoney(),
                "§5» §fGems: §5" + prisonPlayer.getGems(),
                "§d» §fCash: §5" + prisonPlayer.getCash(),
                "",
                "§d★ §fRank: §5" + prisonPlayer.getRank().getName() + " " + prisonPlayer.getTier(),
                "§d★ §fBlocks: " + prisonPlayer.getBlocks(),
                "",
                "mc.mysticube.net"
        );


        boards.put(prisonPlayer.getUuid(), board);

    }

    public static void removeScoreboard(Player p){
        FastBoard board = Scoreboard.boards.remove(p.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }
    public static void updateBlocks(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(7,"§d★ §fBlocks: §5" + prisonPlayer.getBlocks());
    }

    public static void updateMoney(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(2, "§d» §fMoney: §5" + prisonPlayer.getMoney());
    }

    public static void updateGems(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(3, "§5» §fGems: §5" + prisonPlayer.getGems());
    }

    public static void updateCash(PrisonPlayer prisonPlayer){
        boards.get(prisonPlayer.getUuid()).updateLine(4,  "§d» §fCash: §5" + prisonPlayer.getCash());
    }

    public static void updateScoreboard(PrisonPlayer prisonPlayer){
        FastBoard board = boards.get(prisonPlayer.getUuid());

        board.updateLines(
                "§822/02/2024 §714:29",
                "",
                "§d» §fMoney: §5" + prisonPlayer.getMoney(),
                "§5» §fGems: §5" + prisonPlayer.getGems(),
                "§d» §fCash: §5" + prisonPlayer.getCash(),
                "",
                "§d★ §fRank: §5" + prisonPlayer.getRank().getName() + " " + prisonPlayer.getTier(),
                "§d★ §fBlocks: " + prisonPlayer.getBlocks(),
                "",
                "mc.mysticube.net"
        );
    }
}
