package pw.zakharov.gameapi.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * A section of the data.db
 */
public class PlayerData extends DataFile {

    /**
     * Registry with stored player data, by players' name.
     */
    private static final HashMap<String, PlayerData> stored = new HashMap<>();

    private PlayerData(String name) {
        super("players." + name);
    }

    /**
     * Return {@link PlayerData} for a certain player
     *
     * @param player the player
     * @return the player data
     */
    public static PlayerData getFor(Player player) {
        return getFor(player.getUniqueId());
    }

    /**
     * Return {@link PlayerData} representation for a player by their name.
     * <p>
     * Creates new data, if doesn't exists.
     *
     * @param name the player's name
     * @return the data for the player
     */
    public static PlayerData getFor(UUID uuid) {
        synchronized (stored) {
            PlayerData data = stored.get(uuid.toString());

            if (data == null) {
                data = new PlayerData(uuid.toString());

                stored.put(uuid.toString(), data);
            }

            return data;
        }
    }

    /**
     * The amount of Nuggets, an in-game currency, the players has
     */
    public final int getNuggets() {
        load(); // In case other plugin has changed the value

        return getInt("nuggets", 0);
    }

    /**
     * Set nuggets to the player
     *
     * @param nuggets the nuggets, new value
     */
    public final void setNuggets(int nuggets) {
        save("nuggets", nuggets);

        load();
    }
}
