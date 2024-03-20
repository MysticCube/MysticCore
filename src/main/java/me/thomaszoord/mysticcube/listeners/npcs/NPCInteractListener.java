package me.thomaszoord.mysticcube.listeners.npcs;

import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.listeners.npcs.guis.WarriorGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    public static NPC mineNPC = null;
    public static NPC dungeonNPC = null;
    public static NPC plotNPC = null;
    public static NPC spawnerNpc = null;
    public static NPC minersNPC = null;

    @EventHandler
    public void onInteractEvent(NPCRightClickEvent e){
        if(PrisonPlayerManager.getPrisonPlayer(e.getClicker()) == null){
            return;
        }

        if(e.getNPC().equals(mineNPC)){
            MinerGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
            }

        if(e.getNPC().equals(dungeonNPC)){
            WarriorGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
        }


        if(e.getNPC().equals(plotNPC)){
            WarriorGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
        }

        if(e.getNPC().equals(spawnerNpc)){
            WarriorGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
        }


    }



}
