package me.thomaszoord.mysticcube.utils;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class ZUtils {


    public static String formatNumberToK(int i) {
        if (i < 1000) {
            return String.valueOf(i);
        } else {
            int remainder = i % 1000;
            if (remainder == 0) {
                return String.format("%dk", i / 1000);
            } else {
                return String.format("%.1fk", i / 1000.0);
            }
        }
    }




}
