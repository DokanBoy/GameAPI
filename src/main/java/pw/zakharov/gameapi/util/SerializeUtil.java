package pw.zakharov.gameapi.util;

import lombok.NonNull;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import pw.zakharov.gameapi.misc.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for saving or restoring data from YAML files
 */
public class SerializeUtil {

    /**
     * Attempts to convert the given object into something we can
     * store in a YAML file
     *
     * @param object the object
     * @return the converted object
     */
    public static Object serialize(Object object) {
        if (object == null)
            return null;

        if (object instanceof ConfigSerializable)
            return serialize(((ConfigSerializable) object).serialize());

        else if (object instanceof Location)
            return serializeLoc((Location) object);

        else if (object instanceof Enum<?>)
            return object.toString();

        else if (object instanceof Iterable) {
            final List<Object> serialized = new ArrayList<>();

            for (final Object element : (Iterable<?>) object)
                serialized.add(serialize(element));

            return serialized;
        }

        return object;
    }

    /**
     * Converts a {@link Location} object into {@link String}
     *
     * @param loc the location
     * @return the string representation with world, block XYZ coordinates, pitch and yaw, comma separated
     */
    public static String serializeLoc(Location loc) {
        return loc.getWorld().getName() + ", " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + (loc.getPitch() != 0F || loc.getYaw() != 0F ? ", " + loc.getYaw() + ", " + loc.getPitch() : "");
    }

    /**
     * Attempts to go through every item in the list and invoked serialize() method on it
     *
     * @param list the given list
     * @return the serialized list
     */
    public static <T extends ConfigSerializable> List<Object> serializeList(Iterable<T> list) {
        final List<Object> serializedList = new ArrayList<>();

        for (final T t : list)
            serializedList.add(t != null ? t.serialize() : null);

        return serializedList;
    }

    /**
     * Attempts to decode location from {@link #deserializeLocation(Object)}
     *
     * @param object the raw String representation
     * @return the parsed location
     */
    public static Location deserializeLocation(Object object) {
        if (object == null)
            return null;

        final String[] parts = object.toString().split(", ");
        Validate.isTrue(parts.length == 4 || parts.length == 6, "Expected location (string) but got: " + object);

        final String world = parts[0];
        final World bukkitWorld = Bukkit.getWorld(world);
        Validate.isTrue(Bukkit.getWorld(world) != null, "Location with invalid world '" + world + "': " + object);

        final int x = Integer.parseInt(parts[1]), y = Integer.parseInt(parts[2]), z = Integer.parseInt(parts[3]);
        final float yaw = Float.parseFloat(parts.length == 6 ? parts[4] : "0"), pitch = Float.parseFloat(parts.length == 6 ? parts[5] : "0");

        return new Location(bukkitWorld, x, y, z, yaw, pitch);
    }

    /**
     * Attempts to safely parse any number-looking value into Integer.
     * <p>
     * Decimals will be lost.
     *
     * @param object the given object
     * @return the serialized number
     */
    public static int deserializeInt(@NonNull Object object) {
        return Double.valueOf(object.toString()).intValue();
    }

    public static double deserializeDouble(@NonNull Object o) {
        return Double.parseDouble(o.toString());
    }

    public static float deserializeFloat(@NonNull Object o) {
        return Float.parseFloat(o.toString());
    }

    public static boolean deserializeBoolean(@NonNull Object o) {
        return Boolean.parseBoolean(o.toString());
    }
}
