package pw.zakharov.gameapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pw.zakharov.gameapi.data.PlayerData;

/**
 * Internal listener responsible for player-related events.
 */
public final class PlayerListener implements Listener {

    /**
     * Register players' Nuggets.
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Create new data if doesn't exists.
        PlayerData data = PlayerData.getFor(e.getPlayer());
    }
}
