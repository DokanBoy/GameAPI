package pw.zakharov.gameapi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import pw.zakharov.gameapi.data.PlayerData;
import pw.zakharov.gameapi.listener.PlayerListener;
import pw.zakharov.gameapi.registry.ArenaRegistry;

/**
 * The main class of GameAPI library that loads the library as a plugin.
 * <p>
 * To get started using this library, refer to {@link ArenaRegistry}.
 *
 * @author kangarko
 */
public final class GameAPIPlugin extends JavaPlugin {

    /**
     * Get the instance of this library, loaded on the server
     * <p>
     * TIP: To start with, see {@link ArenaRegistry} and {@link PlayerData}
     *
     * @return this instance
     */
    public static GameAPIPlugin getInstance() {
        return (GameAPIPlugin) Bukkit.getPluginManager().getPlugin("GameAPI");
    }

    @Override
    public void onEnable() {
        // Create data folder
        if (!getDataFolder().exists())
            saveResource("data.db", false);

        // Register events
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // Register BungeeCord channel, for ArenaCommands
        final Messenger messenger = getServer().getMessenger();
        if (!messenger.isOutgoingChannelRegistered(this, "BungeeCord"))
            messenger.registerOutgoingPluginChannel(this, "BungeeCord");
    }
}
