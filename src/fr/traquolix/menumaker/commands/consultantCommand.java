package fr.traquolix.menumaker.commands;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.GUIBuilder;
import fr.traquolix.menumaker.creation.InventorySize;
import fr.traquolix.menumaker.usagePluginLoad.DefinitiveGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class consultantCommand implements CommandExecutor {

    private Main plugin;

    public consultantCommand(Main plugin) {
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

        if (args.length != 0) {
            player.sendMessage("Incorrect usage");
        }
        ArrayList<ItemStack> total = new ArrayList<ItemStack>();
        if (DefinitiveGUI.getSize() == 0) {
            player.sendMessage("You do not have created any menus.");
        } else {
            for (int i = 0; i < DefinitiveGUI.getSize(); i++) {
                total.add(GUIBuilder.createItem(Material.ENCHANTED_BOOK, 1, DefinitiveGUI.getName(i), null));
            }
            ItemStack[] thing = new ItemStack[total.size()];
            total.toArray(thing);
            gui.getInv().setContents(thing);
            player.openInventory(gui.getInv());
        }
        return true;
    }
}
