package pw.zakharov.gameapi;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

/**
 * Represents an arena with team support
 */
public interface TeamedArena extends Arena {

    /**
     * Get all teams that play in this arena
     *
     * @return list of all teams
     */
    Set<Team> getTeams();

    /**
     * Get the team of the player
     *
     * @param player the player
     * @return the players team
     */
    Team getTeam(Player player);

    /**
     * Get the original list of players who started to play this arena in a team
     *
     * @param team the team
     * @return the join players list
     */
    List<Player> getStartingTeamPlayers(Team team);

    /**
     * Get the alive and current players in a team
     *
     * @param team the team
     * @return the alive remaining players
     */
    List<Player> getTeamPlayers(Team team);

    /**
     * Give a team to a player
     *
     * @param player the player
     * @param team   the team
     */
    void assignTeam(Player player, Team team);
}
