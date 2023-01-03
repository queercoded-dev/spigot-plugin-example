package dev.queercoded.spigotexample.persistentdata;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerDisabledList implements ConfigurationSerializable {

    public static PlayerDisabledList list; // Static instance of this class

    /**
     * @return A map of the data in this class
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("players", disabledPlayers);

        return map;
    }

    /**
     * @param map A map of the data in this class
     * This constructor is called when the data is loaded from the data file.
     */
    public PlayerDisabledList(Map<String, Object> map) {
        // Create a new instance of this class, this calls the constructor below
        PlayerDisabledList playerDisabledList = new PlayerDisabledList();
        // Set the disabled players list to the list in the map
        playerDisabledList.disabledPlayers = (ArrayList<String>) map.get("players");

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
     */
    public static void set(PlayerDisabledList list) {
        PlayerDisabledList.list = list;
    }

    /**
     * @return The static instance of this class
     */
    public static PlayerDisabledList getList() {
        return list;
    }

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
