package me.thomaszoord.mysticcube.commands;

import me.thomaszoord.mysticcube.commands.impl.ZCommandExecutor;
import me.thomaszoord.mysticcube.listeners.npcs.NPCInteractListener;
import me.thomaszoord.mysticcube.utils.ZUtils;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.Gravity;
import net.citizensnpcs.trait.HologramTrait;
import net.citizensnpcs.trait.SkinTrait;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AdminMinerCommand extends ZCommandExecutor {
    public AdminMinerCommand() {
        super("miner", "mc.admin", "Set the mine npc for teleport");
    }

    @Override
    protected void onCommand(Player p, String[] args) {
            criarNpcMiner(p.getLocation());
            p.sendMessage("§a[MysticCube] NPC Miner successful created!");
    }

    private void criarNpcMiner(Location l){
        NPC miner = CitizensAPI.getNPCRegistry()
                .createNPC(EntityType.PLAYER, "§8[NPC] Miner", l);

        miner.spawn(l);
        miner.data().setPersistent(NPC.Metadata.NAMEPLATE_VISIBLE, false);

        Gravity gravityTrait = miner.getOrAddTrait(Gravity.class);
        gravityTrait.setEnabled(true);

        SkinTrait skinTrait = miner.getOrAddTrait(SkinTrait.class);
        String signature = "jmnXjWHpReIARtiH21mcyqqWIdo0EXc6x5/Caq2KbaqkIbt7tG7DtiDaj5nEV2WproUO4kMfcvBkOVkDno7kCOSX5BS9C1/XR6MKMzKl8lHWxbd27bSgB8zgvFvR59rwz6YkPf76sv2lXaQ0rXa9pbXe3XhtvEC8YtPV7IDmDXUdFR9h3AaSDd53Jnaj2p2njl4DrITjr98vXI2QjpsEXx+ewftCOvkIpUBtcACuOi6I7MPRSkDOTTtNcsdR6KUX6zH7BxzXVhlIC/ZO+wB1qssZrPxAxTEyrmb7EQL2B7UPmLbTYei1MhJprrZBcUExGR6lKNji3tsCAl7hBfPL8PZAaRr8rSzixVEDqdeLFG73HGilyLbK1dYNF42XjVYYN8mwsuAiGqyCf+/2DnC7tEuI9vz6FcV21YBoCKCl+qJP4L4dMrBdshFbC5ZKca3TMMVw3QPGJGXb88IMjNrl9u8cOqBHEqCQ3el3n0hIwr0MY2ydXe4m5ZShgh1nRHnyPr0umQksU3EuvAgp8hcEVkRVqFpP8EmRSkyx5w99Jxk80heym92XeBBvx2RgAUvWjIQqqJtvfFqeDByyisYw36alYuj3+ijeabvFwAw4JJN8d7wXNVvPIBgsiUBegjhKTzzC815fzmftdCrXUg0FTsatVTHIQ2ZF0e107/2jV0o=";
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYzNjg1MTQwMzc5NiwKICAicHJvZmlsZUlkIiA6ICJiMGQ0YjI4YmMxZDc0ODg5YWYwZTg2NjFjZWU5NmFhYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5lU2tpbl9vcmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NmMmZkNzNkNmQ4ZDJmNDk3ZDIxZGQ1ZmJlM2EwNzU1ODA1NzVhZjIxNjM5OGQ1Mzc2ODUxMzdiODFlYzM1YiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

        skinTrait.setSkinPersistent("miner", signature, value);


        HologramTrait hologramTrait = miner.getOrAddTrait(HologramTrait.class);

        hologramTrait.setLineHeight(0.25);
        hologramTrait.addLine("&#878483/mine");
        hologramTrait.addLine("");
        hologramTrait.addLine("&#E8E1E0know about mining on the server!");
        hologramTrait.addLine("&#E8E1E0Here you can fing everything you need to");
        hologramTrait.addLine("");
        hologramTrait.addLine("&#878483(Click to see more)");

        hologramTrait.addLine(ChatColor.BOLD +  "&#51ff5d&lM&#61ff71&lI&#71ff85&lN&#81ff99&lE&#91ffad&lR");

        if(NPCInteractListener.minerNPC != null){
            NPCInteractListener.minerNPC.despawn();
        }

        NPCInteractListener.minerNPC = miner;




    }
}
