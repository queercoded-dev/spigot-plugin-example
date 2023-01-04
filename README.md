# Spigot Example Plugin
This is a an example plugin that adds a ground pound to Minecraft.  
It uses our [Hackathon template](https://github.com/queercoded-dev/spigot-plugin-template).  

## Noteworthy
- CONFIGURATION: The velocity of the ground pound is configurable with a command. This is written to a config.
  - The config is loaded on startup and written to on command.
  - This configuration could also be manually edited. This is how you would do config files in a real plugin.
  - This is done by getting a `YamlConfiguration` in the `persistentdata.ExampleDataLoading` class.
  - This variable is accessed in `listener.PlayerToggleSneakListener` to get the velocity.
  - This variable is modified in the `commands.CommandExec` class.
- SERIALISATION: You can disable the ground pound as a player with `/groundpound off` and re-enable it with `/groundpound on`
  - This data is persistent, it is stored in a file called `data.yml` in the plugin's data folder
  - The data is loaded when the plugin is enabled and saved when the plugin is disabled
  - You can find how this is done in the `persistentdata.PlayerDisabledListener` class. This is loaded with `persistentdata.ExampleDataLoading`.  
    - It uses serialisation to save the data to a file. Bukkit has a serialisation library.  
    - The PlayerDisabledList class must be registered in the plugins main class. This is done in `SpigotExample.java` in the `static` block.  
    - The `PlayerDisabledList` class has a `ArrayList<String>` that is serialised to a file.
    - The constructor `Map<String, Object>` of `PlayerDisabledList` is for deserialization, this gets called when the plugin is enabled.
    - The `serialize()` method is for serialization, this gets called when the plugin is disabled. It returns the `Map<String, Object>` that is written to a file.
- TAB COMPLETION: This example plugin has tab completion for the `/groundpound` command. You can find this in `commands.CommandTabComplete`
  - The Javadoc of this class explains how it works

I didn't include tab completion & serialisation in the template because it's not necessary for every plugin, and is quite complicated.  
But it's good to know how it works, so I included it here.  

### Building
1. Run `gradlew build` in the root directory of the project.
2. The built jar will be in `build/libs`.

This example adds on our template and explains how serialisation, deserialisation and tab completion works.  