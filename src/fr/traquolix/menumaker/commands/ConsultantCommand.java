package fr.traquolix.menumaker.commands;

import com.sun.corba.se.spi.ior.IdentifiableFactoryFinder;
import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.GUIBuilder;
import fr.traquolix.menumaker.creation.InventorySize;
import fr.traquolix.menumaker.usagePluginLoad.DefinitiveGUI;
import fr.traquolix.menumaker.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ConsultantCommand implements CommandExecutor {

    private Main plugin;

    public ConsultantCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("consultant").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd,  String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot execute this command. Only players may execute this command.");
            return true;
        }

        Player player = (Player) sender;
        GUIBuilder gui = new GUIBuilder("Consultant", InventorySize.SIX_LINES, player, -1);

        // Taper le nom d'un menu amène directement à la consultation du menu en question
        // TODO

        ArrayList<ItemStack> total = new ArrayList<ItemStack>();

        if (args.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1); // Delete the last space
            String menuName = sb.toString();
            if (DefinitiveGUI.isDefinitive(menuName)) {
                player.closeInventory();
                player.openInventory(DefinitiveGUI.getInv(menuName));
                return true;
            } else if (DefinitiveGUI.getSize() == 0){
                player.sendMessage("You do not have created any menus.");
                return true;
            } else {
                player.sendMessage("Menu name not found / not matching. Opening Consultant..");
            }
        }

        // Confirm Inventory
        GUIBuilder confirm = new GUIBuilder("Delete ?", InventorySize.THREE_LINES, player, -2);
        ItemStack[] aba = new ItemStack[InventorySize.THREE_LINES.size];
        aba[11] = GUIBuilder.createItem(Material.RED_STAINED_GLASS, 1, Utils.chat("&c&lCancel"));
        aba[15] = GUIBuilder.createItem(Material.GREEN_STAINED_GLASS, 1, Utils.chat("&a&lConfirm"));
        confirm.getInv().setContents(aba);

        // Listener
        new consultantListener(this.plugin, gui, confirm);


        if (DefinitiveGUI.getSize() == 0) {
            player.sendMessage("You do not have created any menus.");
            return true;
        } else {
            for (int i = 0; i < DefinitiveGUI.getSize(); i++) {
                total.add(GUIBuilder.createItem(Material.ENCHANTED_BOOK, 1, DefinitiveGUI.getName(i), Utils.chat("&aLeft &eclick to &7edit&e content. " + "&aRight &eclick to &cdelete&e.")));
            }
            ItemStack[] thing = new ItemStack[total.size()];
            total.toArray(thing);
            gui.getInv().setContents(thing);
            player.openInventory(gui.getInv());
        }

        return true;
    }

}

class consultantListener implements Listener {

    private Main plugin;
    private GUIBuilder gui;
    private  GUIBuilder confirm;
    private int deletionSlot;


    consultantListener(Main plugin, GUIBuilder gui, GUIBuilder confirm) {
        this.plugin = plugin;
        this.gui = gui;
        this.confirm = confirm;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {


        if (e.getCurrentItem() == null) {
            return;
        }

        if (DefinitiveGUI.isDefinitive(e.getSlot())) {
            if (e.getClickedInventory().equals(this.gui.getInv())) {
                if (e.getClick().isRightClick()) {

                    this.deletionSlot = e.getSlot();


                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();

                    e.getWhoClicked().openInventory(confirm.getInv());

                }
                if (e.getClick().isLeftClick()) {
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().openInventory(DefinitiveGUI.getInv(e.getSlot()));
                }
            }
        }

        if (e.getSlot() == 11) {
            if (e.getClickedInventory().equals(this.confirm.getInv())) {
                e.setCancelled(true);
                e.getWhoClicked().sendMessage("Action Cancelled");
                e.getWhoClicked().closeInventory();
            }
        }


        if (e.getSlot() == 15) {
            if (e.getClickedInventory().equals(this.confirm.getInv())) {
                e.setCancelled(true);
                GUIBuilder.delete(deletionSlot);
                e.getWhoClicked().sendMessage("Deletion confirmed");
                e.getWhoClicked().closeInventory();
            }
        }

    }

}
