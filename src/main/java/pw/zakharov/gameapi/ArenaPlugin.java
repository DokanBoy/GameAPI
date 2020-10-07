package pw.zakharov.gameapi;

import org.bukkit.plugin.Plugin;
import pw.zakharov.gameapi.registry.ArenaRegistry;

/**
 * Represents a minigame plugin that uses GameAPI.
 * <p>
 * Typically, your main plugin class should implement this.
 */
public interface ArenaPlugin {

    /**
     * Get the arena manager.
     * <p>
     * If you don't have one, you can just return {@link ArenaRegistry#getArenaManager(ArenaPlugin)}}
     * and that will use our shared manager.
     *
     * @return the arena manager
     */
    ArenaManager getArenas();

    /**
     * Get the name of this plugin
     *
     * @return the name of this plugin
     */
    String getName();

    /**
     * Get the {@link Plugin} representation
     *
     * @return the plugin
     */
    Plugin getPlugin();
}
