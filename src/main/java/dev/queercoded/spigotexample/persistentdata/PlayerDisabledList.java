package dev.queercoded.spigotexample.persistentdata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerDisabledList implements ConfigurationSerializable {

    /**
     * Static instance of this class
     * This is used to access the list of players who have disabled groundpounding
     *
     * We create an instance of this class, then make it the static variable. Check the constructors and set method for more info.
     */
    public static PlayerDisabledList list;

    /**
     * @return A map of the data in this class
     * To serialise, we need to return a Map<String, Object>. The String is the key, and the Object is the value.
     * In Java, an Object can be anything. In this case, it really is the ArrayList of players UUIDs as strings.
     * We can cast it to a ArrayList<String> when we deserialise.
     */
    @Override
    public Map<String, Object> serialize() {
        // Create a new map
        Map<String, Object> map = new HashMap<>();
        // Add the list of players to the map
        map.put("players", disabledPlayers);

        // Return the map
        return map;
    }

    /**
     * @param map A map of the data in this class
     * This constructor is called when the data is loaded from the data file.
     *
     * This class is the deserialise method. It takes as input the map we returned in the serialise method.
     * We can then use the data in the map to create a new instance of this class.
     */
    public PlayerDisabledList(Map<String, Object> map) {
        // Create a new instance of this class, this calls the constructor below
        PlayerDisabledList playerDisabledList = new PlayerDisabledList();
        // Set the disabled players list to the list in the map
        playerDisabledList.disabledPlayers = (ArrayList<String>) map.get("players"); // Get the players list from the map, cast it back to an ArrayList<String>

        // We don't need to set the static instance of this class, because the constructor below will already do that.
    }

    /**
     * Creates a new instance of this class with an empty list
     */
    public PlayerDisabledList() {
        // Set the static instance of this class to this instance
        set(this);
    }

    /**
     * @param list The list to set as the static instance of this class
     *
     * This method is used to set the static instance of this class.
     */
    public static void set(PlayerDisabledList list) {
        PlayerDisabledList.list = list;
    }

    /**
     * @return Get the static instance of this class
     */
    public static PlayerDisabledList getList() {
        return list;
    }

    /**
     * The rest of this class is just a list of players who have disabled groundpounding
     */

    public ArrayList<String> disabledPlayers = new ArrayList<String>();

    /**
     * @param player The player to add to the list
     */
    public void addPlayer(Player player) {
        // Add player to list
        addPlayer(player.getUniqueId().toString());
    }

    /**
     * @param player The player to add to the list
     */
    public void addPlayer(String player) {
        // Add player to list
        if (!disabledPlayers.contains(player)) { // Ensure player is not already in list
            disabledPlayers.add(player);
        }
    }

    /**
     * @param player The player to remove from the list
     */
    public void removePlayer(Player player) {
        // Remove player from list
        removePlayer(player.getUniqueId().toString());
    }

    /**
     * @param player The player to remove from the list
     */
    public void removePlayer(String player) {
        // Remove player from list
        if (disabledPlayers.contains(player)) { // Ensure player is in list
            disabledPlayers.remove(player);
        }
    }

    /**
     * @param player The player to check
     * @return Whether the player is in the list
     */
    public boolean containsPlayer(Player player) {
        if (disabledPlayers.contains(player.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

}
