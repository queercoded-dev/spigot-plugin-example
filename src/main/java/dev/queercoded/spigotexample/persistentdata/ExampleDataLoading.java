package dev.queercoded.spigotexample.persistentdata;

import dev.queercoded.spigotexample.SpigotExample;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ExampleDataLoading {

    private static File dataFile; // File to store data in

    private static YamlConfiguration data; // Get the data from the data file as a YamlConfiguration

    /**
     * @return YamlConfiguration - Data from the data file
     */
    public static YamlConfiguration getData() {
        return data;
    }

    /**
     * Initialise the data file
     */
    public static void init() {
        dataFile = new File(SpigotExample.getPlugin().getDataFolder(), "data.yml"); // Set data file
        if (!dataFile.exists()) { // If data file does not exist, create it
            dataFile.getParentFile().mkdirs();
            SpigotExample.getPlugin().saveResource("data.yml", false); // Don't replace existing file
        }
    }

    /**
     * Write data to file
     */
    public static void writeData() {
        data.set("disabledPlayers", PlayerDisabledList.getList()); // Set data. Because a serialise method is defined in PlayerDisabledList, this will serialise the object into a map.

        // We don't need to set the velocity, because it is already set in the data variable.

        try {
            data.save(dataFile); // Attempt to save data to file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data from the data file
     */
    public static void readData() {
        data = YamlConfiguration.loadConfiguration(dataFile); // This will call the constructor for PlayerDisabledList. See the constructor for more info.

        if (PlayerDisabledList.getList() == null) { // If the list is null, then the constructor was not called. This means that the data file is empty.
            SpigotExample.getPlugin().getLogger().info("Creating new disabled player list");
            new PlayerDisabledList(); // This calls the constructor, which will create a new list.
        }

        // If the velocity is null, then the data file is empty.
        // This shouldn't happen since the default data file has a velocity, but we can check anyway.
        if (data.get("velocity") == null) {
            data.set("velocity", -7.0); // Set the velocity to -7.0 as default
        }

    }

}
