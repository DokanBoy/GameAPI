package org.mineacademy.gameapi;

import org.bukkit.entity.Player;
import org.mineacademy.gameapi.type.ArenaSound;
import org.mineacademy.gameapi.type.MessengerTarget;

/**
 * Represents a way to send messages only to the players inside of an {@link Arena}
 *
 * Variables are automatically replaced by {@link #replaceVariables(String)}
 */
public interface ArenaMessenger {

	/**
	 * Send a message to a player
	 *
	 * @param player the player
	 * @param message the message
	 */
	void tell(Player player, String message);

	/**
	 * Send a message to {@link #getTarget()}
	 *
	 * @param message the message
	 */
	void broadcast(String message);

	/**
	 * Send a message in form of an action bar to {@link #getTarget()}
	 *
	 * @param message the message
	 */
	void broadcastBar(String message);

	/**
	 * Broadcast a sound to {@link #getTarget()}
	 *
	 * @param sound the {@link ArenaSound}
	 * @param pitch the pitch
	 */
	void playSound(ArenaSound sound, float pitch);

	/**
	 * Broadcast a sound to a player
	 *
	 * @param player the player
	 * @param sound the sound
	 * @param pitch the pitch
	 */
	void playSound(Player player, ArenaSound sound, float pitch);

	/**
	 * Return to whom will messages be broadcasted
	 *
	 * @return the target
	 */
	MessengerTarget getTarget();

	/**
	 * Set to whom to broadcast messages
	 *
	 * @param target then new target
	 */
	void setTarget(MessengerTarget target);

	/**
	 * Internal method to replace variables by your plugin
	 *
	 * @param message the message to replace
	 * @return translated message
	 */
	String replaceVariables(String message);
}
