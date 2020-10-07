package pw.zakharov.gameapi;

import org.bukkit.Location;
import pw.zakharov.gameapi.misc.ConfigSerializable;
import pw.zakharov.gameapi.type.SpawnPointType;

/**
 * Represents a simple spawn point
 */
public interface SpawnPoint extends ConfigSerializable {

    /**
     * Get the location of this spawn point
     *
     * @return the location
     */
    Location getLocation();

    /**
     * Get this spawn point type
     *
     * @return the type
     */
    SpawnPointType getType();
}
