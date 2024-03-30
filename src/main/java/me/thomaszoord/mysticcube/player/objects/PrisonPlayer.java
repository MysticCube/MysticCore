package me.thomaszoord.mysticcube.player.objects;

import me.thomaszoord.mysticcube.listeners.player.LobbyJoinEvent;
import me.thomaszoord.mysticcube.listeners.scoreboard.ScoreboardLobby;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.pickaxe.Pickaxe;
import me.thomaszoord.mysticcube.mine.Mine;
import me.thomaszoord.mysticcube.mine.MineSize;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class PrisonPlayer {

    private Player player;
    private UUID uuid;



    //PLAYER COIN
    private double coins;
    private int gems;
    private int cash;


    //PLAYER MINE

    private Mine mine;
    private Pickaxe pickaxe;


    //PLAYER RANK
    private int tier;
    private double points;


    //NEW PLAYER
    public PrisonPlayer(Player player, UUID uuid) {
        this.player = player;
        this.uuid = uuid;
        this.tier = 1;
        this.points = 0;
        this.mine = new Mine(this, MineSize.RANK_1);
        this.pickaxe = new Pickaxe();

        PrisonPlayerManager.addPrisonPlayer(this.player, this);
        equipItens();
        logMessage();
    }


    public void equipItens(){

        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = book.getItemMeta();
        bookMeta.setDisplayName("§a§lGAME GUIDE §8(Click here)");
        bookMeta.setLore(Arrays.asList(
                "§7let yourself be guided by this book so you",
                "§7don't get lost in the wonders of this prison!")
        );
        book.setItemMeta(bookMeta);

        player.getInventory().setItem(8, book);
        player.getInventory().setItem(0, getPickaxe().getPickaxeItemStack());
    }

    public void logMessage(){
        LobbyJoinEvent.sendMessageConsole("§a§l------------------");
        LobbyJoinEvent.sendMessageConsole("§2Player successful created");
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

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins += coins;

    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }


    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public double getPoints() {
        return points;
    }


    public Pickaxe getPickaxe() {
        return pickaxe;
    }


    public void addPoints(double points){
        this.points += points;
    }
    public Mine getMine() {
        return mine;
    }
}
