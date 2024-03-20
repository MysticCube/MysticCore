package me.thomaszoord.mysticcube.utils;

public class IntUtils {


    public static String formatNumberToK(int number) {
        if (number < 1000) {
            return String.valueOf(number);
        } else {
            int remainder = number % 1000;
            if (remainder == 0) {
                return String.format("%dk", number / 1000);
            } else {
                return String.format("%.1fk", number / 1000.0);
            }
        }
    }


    public static String intToRoman(int num) {
        if (num <= 0 || num >= 4000) {
            return null;
        }

        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < romanSymbols.length; i++) {
            while (num >= romanValues[i]) {
                num -= romanValues[i];
                result.append(romanSymbols[i]);
            }
        }

        return result.toString();
    }





}
