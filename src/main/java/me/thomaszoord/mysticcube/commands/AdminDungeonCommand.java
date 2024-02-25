package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Gravity;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.SkinTrait;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AdminDungeonCommand extends ZCommandExecutor {
    public AdminDungeonCommand() {
        super("dungeon", "mc.admin", "Set the dungeon npc for teleport");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
        criarNpc(p.getLocation());
        p.sendMessage("§a[MysticCube] NPC Miner successful created!");
    }

    private void criarNpc(Location l){
        NPC npc = CitizensAPI.getNPCRegistry()
                .createNPC(EntityType.PLAYER, "§8[NPC] Warrior", l);

        npc.spawn(l);
        npc.data().setPersistent(NPC.Metadata.NAMEPLATE_VISIBLE, false);

        Gravity gravityTrait = npc.getOrAddTrait(Gravity.class);
        gravityTrait.setEnabled(true);

        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        String signature = "oubvoAV+bJn+lc/dltGkD6LNiQTPY5jTCgYXUz3tXJPWjBN3c3LPI9E1HiIdX6kdckrVgstVydXg0VT6zILNpMsE+PZY4nKAu3nczWZ1KLlZbvmecaQ+787HQYZXJBcHUl3OJUxD3TUI02O49D6h7YfK4rNCHCcDn6viyjWafbqukxDS+oUFw2ndklY4rMyF86dYbys9J3jiFxYfXPvf48WbuRoQiIin5iV22AzhDEBMtEs88W32amM4vuvLaGzkiENVul4rghEaJ26R6uAtcDRFg6J8eAquG0EOn8Kga2a+Vg9SNlG5wMn11ZHO6ZY4TR+vABbtucHreV3R2Fkx3hz/EdGbvu+SmRqYUBub4qft8bwwQ0Hk365Er6mtoJGpYo0qLwgyH6oMExF9S1EdWmujFgdUeAwIH/l6DfJ48F/P0NcgvRPZ1b7RHql0Mlk+bhptMMwiE6iBYqoYrQ5srwIjKnakcuKqFGteHStPilaYpwBTP5+UFaUIcCeL85Vd5bsFU+fZ/hLzhQSuck1hhm341W9QL456MVAaf9M99aSd3a+p87a1DboVgw1re1grOdmpY/Cy+1O+wKKtG//6ppMGjAFWLHPtoIXBzWRCZVrC6XBjM0NnJICt7awZc2wSlnsBx7n2FkemRu6qMqojGBzgquYmRxSQIpCRq4btAgs=";
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwODYzMDkwMzAyMSwKICAicHJvZmlsZUlkIiA6ICJmYzFhOTdlNTgxM2Y0NDI2YTNmZTI4ZjJiNDc1ZjA4ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJnZXRPbmxpbmVQbGF5ZXJzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdlNDhiOTE2NDkwZWEzNjY3NTk4ZDAzNDA5NWNmYjk3ZWMzNWQwNWU5NTkxMWI5Y2QxYzc0OTcxNTIzZGE0NjUiCiAgICB9CiAgfQp9";

        skinTrait.setSkinPersistent("warrior", signature, value);


        HologramTrait hologramTrait = npc.getOrAddTrait(HologramTrait.class);

        hologramTrait.setLineHeight(0.25);
        hologramTrait.addLine("§7/dungeon");
        hologramTrait.addLine("");
        hologramTrait.addLine("§fnumerous battles, and return victorious!");
        hologramTrait.addLine("§fVenture into this Dungeon, engage in");
        hologramTrait.addLine("");
        hologramTrait.addLine("§8(Click to see more)");

        hologramTrait.addLine("§c§lDUNGEONS");

        if(NPCInteractListener.warriorNpc != null){
            NPCInteractListener.warriorNpc.despawn();
        }

        NPCInteractListener.warriorNpc = npc;




    }
}
