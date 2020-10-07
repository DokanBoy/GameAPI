package pw.zakharov.gameapi;

import org.bukkit.Location;

import java.util.List;

/**
 * Represents signs manager storing all signs
 */
public interface ArenaSigns {

    /**
     * Find a sign at a location
     *
     * @param loc the location
     * @return the sign, or null
     */
    ArenaSign getSignAt(Location loc);

    /**
     * Get all stored signs of a certain type
     *
     * @param type the sign type
     * @return the signs
     */
    List<ArenaSign> getSigns(ArenaSign.SignType type);

    /**
     * Calls {@link ArenaSign#updateState()} for all signs of a certain type in an arena
     *
     * @param type  the sign type
     * @param arena the arena
     */
    void updateSigns(ArenaSign.SignType type, Arena arena);
}