package fr.traquolix.menumaker.utils;

import org.bukkit.ChatColor;


public class Utils {

    /**
     * Make possible the formatting via the well known Essentials formatters : https://wiki.ess3.net/mc/
     * @param s     The String you want to format
     * @return      The formatted String
     */
    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', (ChatColor.RESET + s));
    }
}
