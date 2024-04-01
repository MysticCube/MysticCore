package me.thomaszoord.mysticcube.player.objects;

import me.thomaszoord.mysticcube.listeners.player.PlayerJoinLobbyEvent;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import me.thomaszoord.mysticcube.player.objects.pickaxe.Pickaxe;
import me.thomaszoord.mysticcube.player.objects.mine.Mine;
import me.thomaszoord.mysticcube.player.objects.mine.enums.MineSize;
import me.thomaszoord.mysticcube.utils.packets.TitleAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class PrisonPlayer {

    private final Player player;
    private final UUID uuid;


    //PLAYER COIN
    private double coins;
    private int tokens;
    private int gems;


    //PLAYER MINE
    private Mine mine;
    private Pickaxe pickaxe;


    //PLAYER RANK
    private int tier;
    private double points;
    private double goal;


    //NEW PLAYER
    public PrisonPlayer(Player player, UUID uuid) {
        this.player = player;
        this.uuid = uuid;
        this.tier = 1;
        this.points = 0;
        this.goal = 250;
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
        PlayerJoinLobbyEvent.sendMessageConsole("§a§l------------------");
        PlayerJoinLobbyEvent.sendMessageConsole("§2Player successful created");
        PlayerJoinLobbyEvent.sendMessageConsole("");
        PlayerJoinLobbyEvent.sendMessageConsole("§dPlayer name: §e" + this.getPlayer().getName());
        PlayerJoinLobbyEvent.sendMessageConsole("§dPlayer UUID: §e" + this.getUuid());
        PlayerJoinLobbyEvent.sendMessageConsole("§dPlayer Tier: §e" + this.getTier());
        PlayerJoinLobbyEvent.sendMessageConsole("§d§l------------------");
    }
    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }


    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins += coins;

    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }


    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public double getPoints() {
        return points;
    }


    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public void upTier(){
        
        int newTier = getTier() + 1;

        TitleAPI.sendTitle(player, "§d§lRANKED UP!", "§fThe mine leveled up! §8§m" + getTier() + " §8▸ §7"+ newTier , 20, 50, 20);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);



        player.sendMessage("  ");
        player.sendMessage("          §d§lRANKED UP! §8§m" + getTier() + " §f▸" + newTier);
        player.sendMessage("  §7Congratulations, you've ranked up!");
        player.sendMessage("  §7Now, check below for your rewards:");
        player.sendMessage("  ");


        setTier(newTier);
        setGoal(getGoal() * 2);

    }


    public void addPoints(double points){

        if(getPoints() + points >= getGoal()){
            upTier();
            this.points = 0;
        }

        this.points += points;
    }
    public Mine getMine() {
        return mine;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }
}
