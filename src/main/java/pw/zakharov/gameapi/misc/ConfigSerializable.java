package pw.zakharov.gameapi.misc;

/**
 * A helper indicating that classes extending this can be serialized.
 * <p>
 * A serialized class is typically saved as a HashMap with key value pairs
 * inside a YAML file.
 * <p>
 * Classes implementing this also must implement "public static deserialize"
 * method taking in the same parameter as {@link #serialize()} method outputs
 */
public interface ConfigSerializable {

    /**
     * Return the serialized object, typically a Map
     *
     * @return the serialized object
     */
    Object serialize();
}
