package org.mineacademy.gameapi;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

/**
 * Represents the boss bar indicator, typicall used to display remaining phase time
 */
public interface BossBarIndicator {

	/**
	 * Display the bar to a player
	 *
	 * @param player the player
	 */
	public void showTo(Player player);

	/**
	 * Hide the bar from a player
	 *
	 * @param player the player
	 */
	public void hideFrom(Player player);

	/**
	 * Return if the player is currently viewing the bar
	 *
	 * @param player the player
	 * @return true if player sees the bar
	 */
	public boolean hasBar(Player player);

	/**
	 * Updates the title of this boss bar
	 *
	 * @param title the new title
	 */
	public void updateTitle(String title);

	/**
	 * Updates progress of this boss bar
	 * See {@link BossBar#setProgress(double)}
	 *
	 * @param progress the new progress
	 */
	public void updateProgress(double progress);

	/**
	 * Updates the color of this boss bar
	 *
	 * @param color the new color
	 */
	public void updateColor(ArenaBarColor color);

	/**
	 * Completely hide the bar from everyone
	 */
	public void hide();
}
