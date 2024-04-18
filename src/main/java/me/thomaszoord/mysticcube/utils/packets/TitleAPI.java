package me.thomaszoord.mysticcube.utils.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

public class TitleAPI {
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut){
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, getPacket(EnumWrappers.TitleAction.TITLE, title, fadeIn, stay, fadeOut));
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, getPacket(EnumWrappers.TitleAction.SUBTITLE, subtitle, fadeIn, stay, fadeOut));
    }

    public static void sendTitle(Player player, String title, String subtitle){
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, getPacket(EnumWrappers.TitleAction.TITLE, title, 0, 70, 0));
        ProtocolLibrary.getProtocolManager().sendServerPacket(player, getPacket(EnumWrappers.TitleAction.SUBTITLE, subtitle, 0, 70, 0));
    }

    private static PacketContainer getPacket(EnumWrappers.TitleAction action, String text, int fadeIn, int stay, int fadeOut){
        @SuppressWarnings("deprecation")
        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.TITLE);

        packetContainer.getTitleActions().write(0, action);
        packetContainer.getChatComponents().write(0, getText(text));
        packetContainer.getIntegers().write(0, fadeIn);
        packetContainer.getIntegers().write(1, stay);
        packetContainer.getIntegers().write(2, fadeOut);

        return packetContainer;
    }

    private static WrappedChatComponent getText(String message){
        return WrappedChatComponent.fromJson("{\"text\": \"" + message.replaceAll("&", "§") + "\"}");
    }

}
