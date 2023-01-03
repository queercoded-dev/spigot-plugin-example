package dev.queercoded.spigotexample.listener;

import dev.queercoded.spigotexample.persistentdata.PlayerDisabledList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class PlayerToggleSneakListener implements Listener {

    /**
     * Constructor
     */
    public PlayerToggleSneakListener() {
        // Constructor
    }

    /**
     * @param e PlayerToggleSneakEvent - Event data
     */
    @EventHandler(priority=EventPriority.NORMAL)
    public void onSprint(PlayerToggleSneakEvent e) {

        Player player = e.getPlayer();

        if (player.hasPermission("spigotexample.groundpound") && !PlayerDisabledList.getList().containsPlayer(player)) { // Check if this is a player and that player has perms
            if (!player.isFlying() && e.isSneaking() && !player.isOnGround()) { // Check if they should ground pound
                Vector velocity = player.getVelocity(); // Get current velocity
                player.setVelocity(velocity.setY(-7)); // Set Y velocity to -7
            }
        }

    }
}
