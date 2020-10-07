package pw.zakharov.gameapi;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import pw.zakharov.gameapi.misc.Iconable;

/**
 * Represents a team, if arena supports that
 */
public interface Team extends Iconable {

    /**
     * Get the name of the team
     *
     * @return the teams name
     */
    String getName();

    /**
     * Get {@link #getChatColor()} + {@link #getName()}
     *
     * @return the colored name
     */
    String getFormattedName();

    /**
     * Get the leather color of the team
     * Typically used for helmets.
     *
     * @return the leather color
     */
    DyeColor getColor();

    /**
     * Set the dye color of the team
     *
     * @param color the new color of the team
     */
    void setColor(DyeColor color);

    /**
     * Get the chat color of the team
     * <p>
     * Typically this is calculated from the leather color and cannot be set
     *
     * @return the chat color
     */
    ChatColor getChatColor();

    /**
     * Get the permission to join this team
     *
     * @return the join permission
     */
    String getPermission();

    /**
     * Set the permission to join this team
     *
     * @param permission the join permission
     */
    void setPermission(String permission);

    /**
     * Check if the player is eligible for obtaining this team
     * typically we check if they have join permission
     *
     * @param player the player
     * @return true if player can obtain this team
     */
    boolean mayObtain(Player player);

    /**
     * Gives the player the colored leather helmet
     *
     * @param player the player to give to
     */
    void giveTeamHelmet(Player player);

    /**
     * Permanently remove the team
     */
    void deleteTeam();
}
