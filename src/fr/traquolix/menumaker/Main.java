package fr.traquolix.menumaker;

import fr.traquolix.menumaker.commands.*;
import fr.traquolix.menumaker.usagePluginLoad.DefinitiveGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("MenuMaker started.");
        saveDefaultConfig();

        // Commodities
        DefinitiveGUI.initPluginDefinitiveGUI(this);

        // Commands
        new LoreCommand(this);
        new DisplayNameCommand(this);
        new MakeMenuCommand(this);
        new consultantCommand(this);

        // Listeners

        // GUI

    }

    @Override
    public void onDisable() {
        System.out.println("MenuMaker stopped.");
    }
}
