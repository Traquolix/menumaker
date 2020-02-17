package fr.traquolix.menumaker.creation;

import fr.traquolix.menumaker.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * NOTE : FOR ID, THE AUTO INSTANCES THAT THE PLAYER ARE MAKING ARE 0+
 *                THE INSTANCES THE DEVELOPER (ME) ARE ALL NEGATIVE
 */


public class GUIBuilder implements Listener {

    private Inventory inv;
    private InventoryHolder owner;
    private InventorySize size;
    private String name;
    private int ID;
    private static HashMap<Integer, GUIBuilder> bdd = new HashMap<>();

    /**
     * Build a default GUI
     * @param name Name of the GUI
     * @param numberOfRows Number of rows of the GUI [1-6]
     * @param owner Owner of the GUI
     */
    public GUIBuilder(String name, InventorySize numberOfRows, InventoryHolder owner) {
        this.name = name;
        this.size = numberOfRows;
        this.owner = owner;
        this.ID = 0;
        this.inv = Bukkit.createInventory(this.owner, this.size.size, Utils.chat(this.name));
        bdd.put(this.ID, this);
    }

    /**
     * Build a default GUI, with custom ID (Usually util for the developer (me) for negative ID
     * @param name Name of the GUI
     * @param numberOfRows Number of Rows of the GUI [1-6]
     * @param owner Owner of the GUI
     * @param id Specific ID
     */
    public GUIBuilder(String name, InventorySize numberOfRows, InventoryHolder owner, int id) {
        this.name = name;
        this.size = numberOfRows;
        this.owner = owner;
        this.ID = id;
        this.inv = Bukkit.createInventory(this.owner, this.size.size, Utils.chat(this.name));
    }

    /**
     * Create a bukkit.Inventory via GUI
     * @param gui to make an Inventory
     * @return Inventory created
     */

    public static Inventory InventoryBuilder(GUIBuilder gui) {
        gui.inv = Bukkit.createInventory(gui.getOwner(), gui.getSize().size, Utils.chat(gui.getName()));
        return Bukkit.createInventory(gui.getOwner(), gui.getSize().size, Utils.chat(gui.getName()));
    }

    // Getters
    public InventorySize getSize() {
        return this.size;
    }
    public String getName() {
        return this.name;
    }
    public InventoryHolder getOwner(){
        return this.owner;
    }
    public int getID(){
        return this.ID;
    }
    public static GUIBuilder getGUI(int id){
        return bdd.get(id);
    }
    public Inventory getInv() {
        return this.inv;
    }

    public ItemStack[] getContent() {
        return this.getInv().getContents();
    }

    /**
     * Set the name of the GUI
     * @param owner of the GUI
     */

    public void setOwner(InventoryHolder owner){
        this.owner = owner;
    }

    /**
     * Check if the instance has already been created and not confirmed
     * @param id usually 0
     * @return true if it has been, false if not
     */
    public static boolean alreadyCreated(int id){
        return bdd.containsKey(id);
    }

    /**
     * Check if the instance has already been created and not confirmed
     * @param name Name of the instance (usually "MakeMenu Creator")
     * @return true if it has been, false if not
     */
    public static boolean alreadyCreated(String name){
        for(int i = 0; i < bdd.size(); i++) {
            if (bdd.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Save the inventory into the attribute Inventory
     * @param content Array of ItemStack
     */
    public void saveInv(ItemStack[] content) {
        this.inv.setContents(content);
    }

    /**
     * Get the Inventory via the ID of the instance
     * @param id usually 0
     * @return
     */
    public static Inventory getInvViaID(int id) {
            return bdd.get(id).getInv();

    }

    /**
     * Get the ID of a GUI via it's name.
     * @param name Name of the GUI you are looking for.
     * @return Return the index of the GUI you research. Return sizeof(int) in case not found.
     */
    public static int getID(String name) {
        for(int i = 0; i < bdd.size(); i++) {
            if (bdd.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -2147483647;
    }

    /**
     * Get the current state of the HashMap that contains all the Instances made and not built (Usually 1)
     * @return the Hashmap
     */
    public static HashMap<Integer, GUIBuilder> getTempoHash() {
        return bdd;
    }

    /**
     * Reset the HashMap to 0 item.
     */
    public static void reset(){
        bdd.clear();
    }

    /**
     * Set the name of the GUI
     * @param name of the GUI
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Resize while keeping the items (in the range of your resizing)
     * @param size
     */
    public void resize(InventorySize size) {
        this.size = size;
        this.inv = InventoryBuilder(this);
    }

    /**
     * copy the content of an ItemStack array into another one.
     * @param src
     * @param dest
     * @return dest
     */
    public static ItemStack[] copyContent(ItemStack[] src, ItemStack[] dest) {

        System.arraycopy(src, 0, dest, 0, Math.min(src.length, dest.length));
        return dest;
    }


    /**
     * Create an item stack via it's parameters and integrate it to an inventory (GUIBuilder)
     * @param materialID                ID of the item.
     * @param amount                    Amount to put in the slot.
     * @param displayName               Display Name of the item (hover with the mouse)
     * @param loreString                Display Lore of the item (Paragraph under the title)
     * @return                          Return
     */

    public static ItemStack createItem(Material materialID, int amount, String displayName, String... loreString) {
        ItemStack item;

        item = new ItemStack(materialID, amount);

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(Utils.chat(displayName));

        if (loreString != null) {
            ArrayList lore = new ArrayList();
            for (String s : loreString) {
                lore.add(Utils.chat(s));

            }
            meta.setLore(lore);
        }
        item.setItemMeta(meta);

        return item;
    }
/*
    public static ItemStack[] addItem(int index, ItemStack Item){

    }
*/
    /**
     * inv and content must have the same size, or return null
     * @param inv               The inventory you want to put ItemStacks in.
     * @param content           An array that you fill like the inventory.
     * @return                  Return the inventory filled.
     */

    public static Inventory fillInventory(Inventory inv, ItemStack[] content) {


        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i,content[i]);
        }
        return inv;
    }

}

