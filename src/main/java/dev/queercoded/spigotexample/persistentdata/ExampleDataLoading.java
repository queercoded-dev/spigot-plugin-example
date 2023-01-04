package dev.queercoded.spigotexample.persistentdata;

import dev.queercoded.spigotexample.SpigotExample;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ExampleDataLoading {

    private static File dataFile;

    private static YamlConfiguration data; // Get the data from the data file

    public static void init() {
        dataFile = new File(SpigotExample.getPlugin().getDataFolder(), "data.yml"); // Set data file
        if (!dataFile.exists()) { // If data file does not exist, create it
            dataFile.getParentFile().mkdirs();
            SpigotExample.getPlugin().saveResource("data.yml", false);
        }
    }

    public static void writeData() {
        data.set("disabledPlayers", PlayerDisabledList.getList()); // Set data. Because a serialise method is defined in PlayerDisabledList, this will serialise the object into a map.

        try {
            data.save(dataFile); // Attempt to save data to file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readData() {
        data = YamlConfiguration.loadConfiguration(dataFile); // This will call the constructor for PlayerDisabledList. See the constructor for more info.


        if (PlayerDisabledList.getList() == null) { // If the list is null, then the constructor was not called. This means that the data file is empty.
            SpigotExample.getPlugin().getLogger().info("Creating new disabled player list");
            new PlayerDisabledList(); // This calls the constructor, which will create a new list.
        }

    }

}
