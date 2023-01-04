package dev.queercoded.spigotexample;

import dev.queercoded.spigotexample.command.CommandExec;
import dev.queercoded.spigotexample.command.CommandTabComplete;
import dev.queercoded.spigotexample.listener.PlayerToggleSneakListener;
import dev.queercoded.spigotexample.persistentdata.ExampleDataLoading;
import dev.queercoded.spigotexample.persistentdata.PlayerDisabledList;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotExample extends JavaPlugin {

    static {
        ConfigurationSerialization.registerClass(PlayerDisabledList.class); // Register that PlayerDisabledList is a serialisable class
    }

    /**
     * Plugin Instance
     */
    private static SpigotExample plugin;

    /**
     * Plugin Manager
     */
    private static PluginManager pm;

    /**
     * Run when plugin is enabled
     */
    @Override
    public void onEnable() {
        // Plugin startup logic

        // Set plugin instance
        plugin = this;

        // Set plugin manager
        pm = getServer().getPluginManager();

        // Register commands
        getCommand("groundpound").setExecutor(new CommandExec());
        // Register command tab completor
        getCommand("groundpound").setTabCompleter(new CommandTabComplete());

        pm.registerEvents(new PlayerToggleSneakListener(), this);

        // Register permissions
        pm.addPermission(new Permission("groundpound.groundpound")); // Permission for groundpounding
        pm.addPermission(new Permission("groundpound.toggle")); // Permission for toggling groundpounding
        pm.addPermission(new Permission("groundpound.admin")); // Permission for configuring the velocity of groundpounding

        ExampleDataLoading.init(); // Create data file if it does not exist
        ExampleDataLoading.readData(); // Read data from file
    }

    /**
     * Run when plugin is disabled
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ExampleDataLoading.writeData();

        // Set plugin instance to null
        plugin = null;
    }

    /**
     * @return SpigotTemplate - Plugin instance
     */
    public static SpigotExample getPlugin() {
        return plugin;
    }

}
