package pw.zakharov.gameapi.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

/**
 * Class for compatibility purposes.
 */
public class LegacyAPIUtil {

    /**
     * Represents getOnlinePlayers method in Bukkit
     */
    private static final Method getPlayersMethod;

    /**
     * Is the {@link #getPlayersMethod} returning a {@link Collection} ?
     * <p>
     * Changed in MC 1.7.10.
     */
    private static final boolean isGetPlayersCollection;

    // Called automatically before any static method is invoked
    static {
        try {
            getPlayersMethod = Bukkit.class.getMethod("getOnlinePlayers");
            isGetPlayersCollection = getPlayersMethod.getReturnType() == Collection.class;
        } catch (final Throwable t) {
            throw new RuntimeException("Failed to setup reflection", t);
        }
    }

    /**
     * Returns all online players, compatible on all Bukkit/Spigot versions
     *
     * @return all online players
     */
    public static Collection<? extends Player> getOnlinePlayers() {
        if (isGetPlayersCollection)
            return Bukkit.getOnlinePlayers();

        try {
            return Arrays.asList((Player[]) getPlayersMethod.invoke(null));

        } catch (final ReflectiveOperationException ex) {

            // Should never happen
            throw new RuntimeException("Reflection malfunction", ex);
        }
    }
}
