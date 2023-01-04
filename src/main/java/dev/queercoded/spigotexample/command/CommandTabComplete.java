package dev.queercoded.spigotexample.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTabComplete implements TabCompleter {

    /**
     *
     * This will be called every time a player types a key with this command. Therefore the args will be the
     * arguments that the player has already typed. The player will be the player that typed the command.
     * You can return a list of strings that will be used to auto complete the command. If you return null
     * then the command will not be auto completed.
     *
     * Since the args contains the arguments that the player has already typed, you can use that to determine
     * what you want to return. Here, the options are "on", "off", and "toggle". If the player has already
     * typed "t", then we will only return "toggle".
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside of a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) { // Gets called every time a player types a key with this command
        if (sender instanceof Player && sender.hasPermission("groundpound.toggle")) { // Check if sender is player and has permission
            if (args.length <= 1) { // Only do this for the first argument

                ArrayList<String> tabComplete = new ArrayList<>(); // Create a new list to store the tab complete options

                for (String s : Arrays.asList("on", "off", "toggle")) { // Loop through the options
                    if (s.startsWith(args[0])) { // If the argument starts with the argument the player has typed
                        tabComplete.add(s); // Add it to the list
                    }
                }
                return tabComplete; // Return the list
            }
        }
        return null; // Return null if the sender does not have permission or the arguments are greater than 1
    }
}
