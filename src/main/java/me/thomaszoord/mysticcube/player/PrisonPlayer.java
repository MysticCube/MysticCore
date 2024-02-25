package me.thomaszoord.mysticcube.player;

import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import me.thomaszoord.mysticcube.listeners.scoreboard.Scoreboard;
import me.thomaszoord.mysticcube.player.ranks.PrisonRanks;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PrisonPlayer {

    private Player player;
    private UUID uuid;


    //PLAYER COIN
    private int money;
    private int gems;
    private int blocks = 0;
    private int cash;




    //PLAYER RANK
    private PrisonRanks rank;
    private int tier;




    //IF
    public PrisonPlayer(Player player, UUID uuid) {
        this.player = player;
        this.uuid = uuid;
        this.rank = PrisonRanks.DIRT;
        this.tier = 1;

        PrisonPlayerManager.addPrisonPlayer(this.player, this);
        logMessage();
    }


    public void logMessage(){
        LobbyJoinEvent.sendMessageConsole("§a§l------------------");
        LobbyJoinEvent.sendMessageConsole("§2Player sucessful created");
        LobbyJoinEvent.sendMessageConsole("");
        LobbyJoinEvent.sendMessageConsole("§dPlayer name: §e" + this.getPlayer().getName());
        LobbyJoinEvent.sendMessageConsole("§dPlayer UUID: §e" + this.getUuid());
        LobbyJoinEvent.sendMessageConsole("§dPlayer rank: §e" + this.getRank().getName());
        LobbyJoinEvent.sendMessageConsole("§dPlayer Tier: §e" + this.getTier());
        LobbyJoinEvent.sendMessageConsole(" ");
        LobbyJoinEvent.sendMessageConsole("Coins: ");
        LobbyJoinEvent.sendMessageConsole("§dPlayer Gem: §e" + this.getGems());
        LobbyJoinEvent.sendMessageConsole("§dPlayer Money: §e" + this.getMoney());
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

    public PrisonRanks getRank() {
        return rank;
    }

    public void setRank(PrisonRanks rank) {
        this.rank = rank;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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
        Scoreboard.updateBlocks(this);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
