package fr.traquolix.menumaker;

import fr.traquolix.menumaker.commands.*;
import fr.traquolix.menumaker.usagePluginLoad.DefinitiveGUI;
import fr.traquolix.menumaker.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("&a[&6Menu Maker&a] &r: &cMenuMaker started.");
        saveDefaultConfig();

        // Commodities
        DefinitiveGUI.initPluginDefinitiveGUI(this);

        // Commands
        new LoreCommand(this);
        new DisplayNameCommand(this);
        new MakeMenuCommand(this);
        new MenuListCommand(this);
        new ItemEditCommand(this);

        // Listeners

        // GUI

    }

    @Override
    public void onDisable() {
        System.out.println("[Menu Maker] : MenuMaker stopped.");
    }
}
