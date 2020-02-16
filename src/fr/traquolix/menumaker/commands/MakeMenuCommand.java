package fr.traquolix.menumaker.commands;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.InventorySize;
import fr.traquolix.menumaker.usagePluginLoad.DefinitiveGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.traquolix.menumaker.creation.GUIBuilder;
import org.bukkit.inventory.ItemStack;


public class MakeMenuCommand implements CommandExecutor {
    private Main plugin;
    private int iterator = DefinitiveGUI.getSize();

    public MakeMenuCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("makemenu").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot execute this command. Only players may execute this command.");
            return true;
        }

        Player player = (Player) sender;
        String title = " ";


        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("build")) {
                if (args[1] == null) {
                    player.sendMessage("Missing arguments");
                    return true;
                } else { // Make the name of the GUI
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }
                    sb.deleteCharAt(sb.length()-1); // Delete the last space
                    String menuName = sb.toString();
                    if (!(GUIBuilder.alreadyCreated("MakeMenu Creator"))) {
                        player.sendMessage("You have to create a menu first via /makemenu");
                        return true;
                    } else if (!(DefinitiveGUI.isDefinitive(menuName))) {
                        int id = GUIBuilder.getID("MakeMenu Creator");
                        GUIBuilder.getGUI(id).setName(menuName);
                    } else {
                        player.sendMessage("Menu already existing.");
                        return true;
                    }

                }

                new DefinitiveGUI(); // Copy the content of the GUIBuilder HashMap in the DefinitiveGUI Hashmap and erase it.
                GUIBuilder.reset();
                iterator++;

            } else {
                player.sendMessage("Unknown function");
                return true;
            }
        } else if (args.length == 0) { // Create a 3 lines inventory makemenu by default
            if (GUIBuilder.alreadyCreated(0)) { // If it's already existing, open it
                GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                player.openInventory(GUIBuilder.getInvViaID(0));
                GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
            } else { // If not, create it to open it
                GUIBuilder makeMenu_creator = new GUIBuilder("MakeMenu Creator", InventorySize.THREE_LINES, player);
                int id = makeMenu_creator.getID();
                player.openInventory(GUIBuilder.getInvViaID(id));
            }
        } else if (args[0].matches("[1-6]")) { // If the number of lines used in arguments are between 1 and 6 (the max and min size of a minecraft inventory)
            int value = Integer.parseInt(args[0]);

            if (GUIBuilder.alreadyCreated(0)) { // Resize shit i don't want to talk about. Mostly mess that take the old makemenu and resize it to display it without losing the items in it
                ItemStack[] legacy = GUIBuilder.getInvViaID(0).getContents();
                switch (value) {
                    case 1:
                        GUIBuilder.getGUI(0).resize(InventorySize.ONE_LINE);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 2:
                        GUIBuilder.getGUI(0).resize(InventorySize.TWO_LINES);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 3:
                        GUIBuilder.getGUI(0).resize(InventorySize.THREE_LINES);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 4:
                        GUIBuilder.getGUI(0).resize(InventorySize.FOUR_LINES);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 5:
                        GUIBuilder.getGUI(0).resize(InventorySize.FIVE_LINES);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 6:
                        GUIBuilder.getGUI(0).resize(InventorySize.SIX_LINES);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.copyContent(legacy, GUIBuilder.getInvViaID(0).getContents()));;
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    default:
                        player.sendMessage("Plugin error");
                        return false;
                }
            } else { // Make a new inventory of the size asked if the makemenu default hasn't been created
                switch (value) {
                    case 1:
                        new GUIBuilder("MakeMenu Creator", InventorySize.ONE_LINE, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 2:
                        new GUIBuilder("MakeMenu Creator", InventorySize.TWO_LINES, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 3:
                        new GUIBuilder("MakeMenu Creator", InventorySize.THREE_LINES, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 4:
                        new GUIBuilder("MakeMenu Creator", InventorySize.FOUR_LINES, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 5:
                        new GUIBuilder("MakeMenu Creator", InventorySize.FIVE_LINES, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    case 6:
                        new GUIBuilder("MakeMenu Creator", InventorySize.SIX_LINES, player);
                        GUIBuilder.getInvViaID(0).setContents(GUIBuilder.getInvViaID(0).getContents());
                        player.openInventory(GUIBuilder.getInvViaID(0));
                        GUIBuilder.getGUI(0).saveInv(GUIBuilder.getInvViaID(0).getContents());
                        return true;
                    default:
                        player.sendMessage("Plugin error");
                        return false;
                }
            }
        } else { // Will put other usage for commands of makemenu if I make new ones
            if (!(args[0].equalsIgnoreCase("build"))) {
                player.sendMessage("Incorrect usage or impossible size");
            } else {
                player.sendMessage("Missing arguments");
            }
        }

        return true;
    }
}