package fr.traquolix.menumaker.commands;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class LoreCommand implements CommandExecutor {

    public Main plugin;

    public LoreCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("lore").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&a[&6Menu Maker&a] &r: &cYou cannot execute this command. Only players may execute this command"));
            return true;
        }
        Player player = (Player) sender;

        // Checking...
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

        // Check if hold item is null
        if (item.getAmount() == 0) {
            player.sendMessage(Utils.chat("&a[&6Menu Maker&a] &r: &cYou cannot put lore to nothing"));
            return true;
        }

        // Check args length. If no argument, reset lore.
        assert meta != null;
        if (args.length == 0) {
            meta.setLore(null);
            item.setItemMeta(meta);
            return true;
        }

        // Making ArrayList to store the lore with the for each arg
        ArrayList<String> lore = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
            sb.append(" ");
        }

        // If item has lore, redefine lore to add lines
        if (meta.hasLore()) {
            lore = (ArrayList<String>) meta.getLore();
        }

        // Add lines via the StringBuffer
        assert lore != null;
        lore.add(Utils.chat(sb.toString()));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return true;

    }
}
