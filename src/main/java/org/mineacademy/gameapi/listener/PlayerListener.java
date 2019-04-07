package org.mineacademy.gameapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mineacademy.gameapi.data.PlayerData;

/**
 * Internal listener responsible for player-related events.
 */
public final class PlayerListener implements Listener {

	/**
	 * Register players' Nuggets.
	 */
	@EventHandler(priority=EventPriority.LOWEST)
	public final void onPlayerJoin(PlayerJoinEvent e) {

		// Create new data if doesn't exists.
		PlayerData.getFor(e.getPlayer());
	}
}
