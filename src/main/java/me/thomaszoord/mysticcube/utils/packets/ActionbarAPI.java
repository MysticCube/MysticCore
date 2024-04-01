package me.thomaszoord.mysticcube.utils.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

public class ActionbarAPI {

    public static void sendActionBarMessage(Player p, String textMessage){
        ProtocolLibrary.getProtocolManager().sendServerPacket(p, getPacket(textMessage));

    }

    private static PacketContainer getPacket(String textMessage){
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.CHAT);
        packetContainer.getChatComponents().write(0, getText(textMessage));
        packetContainer.getBytes().write(0, (byte) 2);

       return packetContainer;
    }


    private static WrappedChatComponent getText(String message){
        return WrappedChatComponent.fromJson("{\"text\": \"" + message.replaceAll("&", "§") + "\"}");
    }


}
