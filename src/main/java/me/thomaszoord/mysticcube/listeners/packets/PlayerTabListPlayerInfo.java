package me.thomaszoord.mysticcube.listeners.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketTypeEnum;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class PlayerTabListPlayerInfo extends PacketAdapter {


    public static Set<String> ignoreTabList = new HashSet<>();

    public PlayerTabListPlayerInfo(Plugin plugin){
        super(plugin, ListenerPriority.HIGH, PacketType.Play.Server.PLAYER_INFO);
    }

    @Override
    public void onPacketSending(PacketEvent event){
        String name = event.getPacket().getStrings().read(0);

        if(ignoreTabList.contains(name)){
            event.setCancelled(true);
        }

    }


    public static void ignore(Player player) {
        ignoreTabList.add(player.getPlayerListName());
    }

    public static void unignore(Player player) {
        ignoreTabList.remove(player.getPlayerListName());
    }

}
