package me.thomaszoord.mysticcube.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ZUtils {


    public static String sendGradientMessage(String message, ChatColor startColor, ChatColor endColor) {
        int messageLength = message.length();
        int startColorCode = startColor.getColor().hashCode();
        int endColorCode = endColor.getColor().hashCode();

        StringBuilder gradientMessage = new StringBuilder();

        for (int i = 0; i < messageLength; i++) {
            double ratio = ((double) i) / ((double) messageLength - 1);
            char color = (char) ((int) (startColorCode + (endColorCode - startColorCode) * ratio));
            gradientMessage.append(ChatColor.getByChar(color)).append(message.charAt(i));
        }

        return gradientMessage.toString();
    }

}
