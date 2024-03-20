package me.thomaszoord.mysticcube.player;

import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticmine.mine.Mine;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PrisonPlayer {

    private Player player;
    private UUID uuid;



    //PLAYER COIN
    private int coins;
    private int gems;
    private int blocks;
    private int cash;


    //PLAYER MINE


    //PLAYER RANK
    private int tier;
    private int points;


    //NEW PLAYER
    public PrisonPlayer(Player player, UUID uuid) {
        this.player = player;
        this.uuid = uuid;
        this.tier = 1;
        this.points = 0;
        this.goal = 250;
        this.blocks = 0;

        PrisonPlayerManager.addPrisonPlayer(this.player, this);
        logMessage();
    }


    public void logMessage(){
        LobbyJoinEvent.sendMessageConsole("§a§l------------------");
        LobbyJoinEvent.sendMessageConsole("§2Player sucessful created");
        LobbyJoinEvent.sendMessageConsole("");
        LobbyJoinEvent.sendMessageConsole("§dPlayer name: §e" + this.getPlayer().getName());
        LobbyJoinEvent.sendMessageConsole("§dPlayer UUID: §e" + this.getUuid());
        LobbyJoinEvent.sendMessageConsole("§dPlayer Tier: §e" + this.getTier());
        LobbyJoinEvent.sendMessageConsole(" ");
        LobbyJoinEvent.sendMessageConsole("Coins: ");
        LobbyJoinEvent.sendMessageConsole("§dPlayer Gem: §e" + this.getGems());
        LobbyJoinEvent.sendMessageConsole("§dPlayer Money: §e" + this.getCoins());
        LobbyJoinEvent.sendMessageConsole("§dPlayer Cash: §e" + this.getCash());
        LobbyJoinEvent.sendMessageConsole("");
        LobbyJoinEvent.sendMessageConsole("§aMysticCube log");
        LobbyJoinEvent.sendMessageConsole("§d§l------------------");
    }
    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
        ScoreboardLobby.updateGems(this);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPoints() {
        return points;
    }


    public int getGoal() {
        return goal;
    }

}
