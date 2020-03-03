package fr.traquolix.menumaker.commands;

import fr.traquolix.menumaker.Main;
import fr.traquolix.menumaker.creation.GUIBuilder;
import fr.traquolix.menumaker.creation.InventorySize;
import fr.traquolix.menumaker.utils.Utils;
import javafx.scene.effect.Glow;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ItemEditCommand implements CommandExecutor {

    private Main plugin;

    public ItemEditCommand(Main plugin) {
        this.plugin = plugin;

        plugin.getCommand("itemedit").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender ,Command cmd ,String s ,String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&a[&6Menu Maker&a] &r: &cYou cannot execute this command. Only players may execute this command."));
            return true;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if hold item is null
        if (item.getAmount() == 0) {
            player.sendMessage(Utils.chat("&a[&6Menu Maker&a] &r: &cYou cannot edit nothing"));
            return true;
        }


        GUIBuilder gui = new GUIBuilder("Item Editor", InventorySize.THREE_LINES, player, -3); // ID -3
        ItemStack[] content = new ItemStack[InventorySize.THREE_LINES.size];
        String GlowOnOff;
        String LinkedTo = null;

        // FAIRE LE CHECK DE L'ENCHANTEMENT SILK TOUCH DE L'ITEM DANS LA MAIN
        // TODO

        item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 0);

        if (item.containsEnchantment(Enchantment.SILK_TOUCH)) {
            GlowOnOff = Utils.chat("&a&lON");
        } else {
            GlowOnOff = Utils.chat("&c&lOFF");
        }

        content[11] = GUIBuilder.createItem(Material.WOODEN_SWORD, 1, Utils.chat("&9&lGlow effect &r&7: &r" + GlowOnOff));
        content[11].addUnsafeEnchantment(Enchantment.SILK_TOUCH, 0);
        ItemMeta meta = content[11].getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        content[11].setItemMeta(meta);


        if (LinkedTo == null) {
            LinkedTo = Utils.chat("&c&lNONE");
        }

        content[12] = GUIBuilder.createItem(Material.DARK_OAK_PRESSURE_PLATE, 1, Utils.chat("&9&lLinked to &r&7: &r" + LinkedTo));
        content[12].getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);


        gui.getInv().setContents(content);

        player.openInventory(gui.getInv());

        return true;
    }

}

class ItemEditListener implements Listener {

}
