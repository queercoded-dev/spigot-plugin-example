package dev.queercoded.spigotexample.command;

import dev.queercoded.spigotexample.persistentdata.ExampleDataLoading;
import dev.queercoded.spigotexample.persistentdata.PlayerDisabledList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExec implements CommandExecutor {

    /**
     * Run when command is executed, must result a boolean for success
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String... args) {
        /*
            An example command, replace this with your own command logic
            This will send a message to the sender when the command is executed with the label and arguments
            When "test" is given, it will return "Test command executed" to the sender
            When "echo" is given, it will return the arguments to the sender
            It always returns true to indicate the command was executed successfully to the server
         */

        // Check if the sender has the permission defined earlier and is a player
        if (sender instanceof Player && sender.hasPermission("groundpound.toggle")) {
            Player player = (Player) sender; // Cast sender to player
            // Get the list of players who have disabled ground pound
            PlayerDisabledList list = PlayerDisabledList.getList();
            if (args.length > 0) { // If arguments are given
                switch (args[0]) {
                    case "on":
                        list.removePlayer(player);
                        player.sendMessage("Ground pound enabled");
                        break;
                    case "off":
                        list.addPlayer(player);
                        player.sendMessage("Ground pound disabled");
                        break;
                    case "toggle":
                        if (list.containsPlayer(player)) {
                            list.removePlayer(player);
                            player.sendMessage("Ground pound enabled");
                        } else {
                            list.addPlayer(player);
                            player.sendMessage("Ground pound disabled");
                        }
                        break;
                    case "setvelocity": // For admins
                        if (player.hasPermission("groundpound.admin")) { // Check if the player has permission
                            if (args.length > 1) {
                                try {
                                    double velocity = Double.parseDouble(args[1]);
                                    ExampleDataLoading.getData().set("velocity", velocity);
                                    player.sendMessage("Ground pound velocity set to " + velocity);
                                } catch (NumberFormatException e) {
                                    player.sendMessage("Invalid velocity");
                                }
                            } else {
                                player.sendMessage("Velocity not given");
                            }
                        } else {
                            player.sendMessage("You do not have permission to do that");
                        }
                        break;
                    // If none of the above, send message
                    default:
                        player.sendMessage("Invalid argument");
                        break;
                }
            // When no arguments are given
            } else {
                if (list.containsPlayer(player)) {
                    player.sendMessage("Ground pound is currently disabled");
                } else {
                    player.sendMessage("Ground pound is currently enabled");
                }
            }
        } else {
            sender.sendMessage("You do not have permission to execute this command");
        }

        return true; // Shouldn't fail
    }
}
