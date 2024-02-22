package me.thomaszoord.mysticcube.listeners.npcs;

import me.thomaszoord.mysticcube.listeners.npcs.guis.MinerGUI;
import me.thomaszoord.mysticcube.listeners.npcs.guis.WarriorGUI;
import me.thomaszoord.mysticcube.player.PrisonPlayerManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    public static NPC minerNPC = null;
    public static NPC warriorNpc = null;

    @EventHandler
    public void onInteractEvent(NPCRightClickEvent e){
        if(PrisonPlayerManager.getPrisonPlayer(e.getClicker()) == null){
            return;
        }

        if(e.getNPC().equals(minerNPC)){
            MinerGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
            }

        if(e.getNPC().equals(warriorNpc)){
            WarriorGUI.mineGUI(PrisonPlayerManager.getPrisonPlayer(e.getClicker()));
            return;
        }
    }



    public String greenColor(String s){
        return "&#2eff62"+s;
    }


}
