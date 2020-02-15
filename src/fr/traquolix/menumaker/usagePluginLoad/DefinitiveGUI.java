package fr.traquolix.menumaker.usagePluginLoad;

import fr.traquolix.menumaker.creation.GUIBuilder;

import java.util.HashMap;

public class DefinitiveGUI {

    private static HashMap<Integer, GUIBuilder> bddFinal = new HashMap<>();
    private static int ID = 0;

    /**
     * Copy/Paste makemenu HashMap to make it definitive until the shutdown of the plugin (for the moment) and count it via ID.
     */
    public DefinitiveGUI(){
        bddFinal.put(ID,GUIBuilder.getTempoHash().get(0));
        ID++;

    }

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
