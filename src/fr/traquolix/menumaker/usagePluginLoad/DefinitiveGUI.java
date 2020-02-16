package fr.traquolix.menumaker.usagePluginLoad;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.GUIBuilder;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;

public class DefinitiveGUI {

    private static HashMap<Integer, GUIBuilder> bddFinal = new HashMap<>();
    private static int ID = 0;
    private static Main plugin;

    /**
     * Copy/Paste makemenu HashMap to make it definitive until the shutdown of the plugin (for the moment) and count it via ID.
     */
    public DefinitiveGUI() {
        bddFinal.put(ID, GUIBuilder.getTempoHash().get(0));
        ID++;
    }

    public static void initPluginDefinitiveGUI(Main plugin) {
            DefinitiveGUI.plugin = plugin;
    }
/*
    public static void setConfig(){
        for (int i = getSize()-1; i != -1; i--) {
            plugin.getConfig().set("GUI" + DefinitiveGUI.getDefinitiveHash().get(i).getID() + ".name", DefinitiveGUI.getDefinitiveHash().get(i).getName());
            plugin.getConfig().set("GUI" + DefinitiveGUI.getDefinitiveHash().get(i).getID() + ".size", DefinitiveGUI.getDefinitiveHash().get(i).getSize().toString());
            plugin.getConfig().set("GUI" + DefinitiveGUI.getDefinitiveHash().get(i).getID() + ".owner", DefinitiveGUI.getDefinitiveHash().get(i).getOwner().toString());
            plugin.getConfig().set("GUI" + DefinitiveGUI.getDefinitiveHash().get(i).getID() + ".ID", DefinitiveGUI.getDefinitiveHash().get(i).getID());
            for (ItemStack item : DefinitiveGUI.getDefinitiveHash().get(i).getContent()) {
                plugin.getConfig().set("GUI" + DefinitiveGUI.getDefinitiveHash().get(i).getID() + ".Inventory.Items", item);
            }
        }
    }
    public static void add(GUIBuilder gui) {
        bddFinal.put(ID, gui);
        ID++;
    }

 */
    /**
     * Ask if a menu already exist or not in the definitive HashMap
     * @param name String. Has to be exactly the one you putted to create the menu. (Essentials formatting included).
     * @return true if found, false if not.
     */
    public static boolean isDefinitive(String name) {
        for(int i = 0; i < bddFinal.size(); i++) {
            if (bddFinal.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Count how many menus you built
     * @return the number
     */
    public static int getSize() {
        return bddFinal.size();
    }

    /**
     * Get the name via ID. (Creation date, pretty much)
     * @param i id of the GUI you want to get
     * @return the name of the GUI
     */
    public static String getName(int i) {
        return bddFinal.get(i).getName();
    }

    /**
     * return the entire HashMap
     * @return the entire HashMap
     */
    public static HashMap<Integer, GUIBuilder> getDefinitiveHash() {
        return bddFinal;
    }

}
