package fr.traquolix.menumaker.usagePluginLoad;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.GUIBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

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
        refreshInv();
        ID++;
    }

    /**
     * Refresh the Display name AND the owner of each GUI when going from Temporary HashMap to DefinitiveHashMap
     */
    private void refreshInv() {
        Bukkit.broadcastMessage(String.valueOf(DefinitiveGUI.getSize()));
        for (int i = 0; i < DefinitiveGUI.getSize(); i++) {
            ItemStack[] legacy = DefinitiveGUI.getDefinitiveHash().get(i).getContent();
            InventoryHolder legacyHolder = DefinitiveGUI.getDefinitiveHash().get(i).getOwner();
            GUIBuilder.InventoryBuilder(bddFinal.get(i));
            bddFinal.get(i).setOwner(legacyHolder);
            bddFinal.get(i).getInv().setContents(legacy);
        }
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
     *
     * @param name String. Has to be exactly the one you putted to create the menu. (Essentials formatting included).
     * @return true if found, false if not.
     */
    public static boolean isDefinitive(String name) {
        for (int i = 0; i < bddFinal.size(); i++) {
            if (bddFinal.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a GUI is contained in the Defintive HashMap.
     *
     * @param ID is the ID OF THE DEFINITIVE HASH MAP YOU HAVE TO CHECK
     * @return true if found, false if not.
     */
    public static boolean isDefinitive(int ID) {
        return bddFinal.containsKey(ID);
    }

    /**
     * Count how many menus you built
     *
     * @return the number
     */
    public static int getSize() {
        return bddFinal.size();
    }

    /**
     * Get the name via ID. (Creation date, pretty much)
     *
     * @param i id of the GUI you want to get
     * @return the name of the GUI
     */
    public static String getName(int i) {
        return bddFinal.get(i).getName();
    }

    /**
     * return the entire HashMap
     *
     * @return the entire HashMap
     */
    public static HashMap<Integer, GUIBuilder> getDefinitiveHash() {
        return bddFinal;
    }

    public static void setDefinitiveHash(HashMap<Integer, GUIBuilder> newBdd) {
        bddFinal = newBdd;
    }

    /**
     * Return inventory for Defintiive content
     *
     * @param name research if the inventory exists in Definitive HashMap
     * @return Inventory if found, null if not.
     */
    public static Inventory getInv(String name) {
        for (int i = 0; i < bddFinal.size(); i++) {
            if (bddFinal.get(i).getName().equals(name)) {
                return bddFinal.get(i).getInv();
            }
        }
        return null;
    }

    /**
     * Get the inventory of a GUI in Definitive HashMap
     *
     * @param ID Warning : It's the ID of the Definitive HashMap you have to put. Do not work if you put an ID above the size of the Definitive HashMap. Get DefitiveGUI.getsize()-1 to check or DefinitiveGUI.isDefinitive(ID) .
     * @return Inventory of the GUI stored in the Definitive HashMap at this ID.
     */
    public static Inventory getInv(int ID) {
        return bddFinal.get(ID).getInv();
    }

    public static void reBlock(int start) {
        Bukkit.broadcastMessage(String.valueOf(bddFinal.size()));
        for (int i = start; i < bddFinal.size(); i++) {
            if (!(bddFinal.containsKey(i))) {
                if (bddFinal.containsKey(i+1)) {
                    bddFinal.put(i, bddFinal.get(i+1));
                    bddFinal.remove(i+1);
                    ID--;
                }
            }
        }
    }
}
