package pw.zakharov.gameapi.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import pw.zakharov.gameapi.ArenaManager;
import pw.zakharov.gameapi.ArenaPlugin;
import pw.zakharov.gameapi.registry.ArenaRegistry;

/**
 * Represents a dummy arena plugin using a shared arena manager.
 */
@RequiredArgsConstructor
public final class DummyPlugin implements ArenaPlugin {

    /**
     * The JavaPlugin
     */
    @Getter
    private final Plugin plugin;

    /**
     * The plugins name
     */
    @Override
    public String getName() {
        return plugin.getName();
    }

    /**
     * Should return arena registry but this is unsupported in dummy plugins, use {@link ArenaRegistry} instead
     *
     * @throws UnsupportedOperationException this is not supported
     * @deprecated unsupported
     */
    @Deprecated
    @Override
    public ArenaManager getArenas() {
        throw new UnsupportedOperationException("Use ArenaRegistry to get ArenaManager");
    }
}