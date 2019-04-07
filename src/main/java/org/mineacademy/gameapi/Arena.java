package org.mineacademy.gameapi;

import java.util.Collection;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.gameapi.cause.DeathCause;
import org.mineacademy.gameapi.cause.JoinCause;
import org.mineacademy.gameapi.cause.LeaveCause;
import org.mineacademy.gameapi.cause.StopCause;
import org.mineacademy.gameapi.event.ArenaPreLeaveEvent;
import org.mineacademy.gameapi.type.ArenaState;

/**
 * Represents a game arena.
 *
 * There are other interfaces that extends this, see {@link TeamedArena} for example.
 */
public interface Arena {

	/**
	 * Attempts to join a player to this arena.
	 *
	 * @param player the player
	 * @param cause why is the player joining
	 * @throws EventHandledException when the event is handled in the pipeline
	 *
	 * @return if the player was joined, false if conditions didn't allow it
	 */
	public boolean joinPlayer(Player player, JoinCause cause);

	/**
	 * Attempts to kick a player from this arena or
	 * throws an error when he's not playing or the arena is not running.
	 *
	 * @param player the player
	 * @param cause why is the player quitting
	 * @return if the player was kicked, false if the {@link ArenaPreLeaveEvent} was cancelled
	 */
	public boolean kickPlayer(Player player, LeaveCause cause);

	/**
	 * Attempts to start the lobby. This is typically called when the arena is stopped and first player has joined.
	 *
	 * Typically, you should update your signs here, start countdown till the arena starts and restore initial snapshot.
	 */
	public void startLobby();

	/**
	 * Attempts to start the arena immediately, typically called after the lobby has started, see {@link #startLobby()}
	 *
	 * @return true if the arena was started successfully
	 */
	public boolean startArena();

	/**
	 * Attempts to stop the arena immediately
	 *
	 * @param cause, the reason why it is being stopped
	 */
	public void stopArena(StopCause cause);

	/**
	 * Moves an active arena player to his last saved location, or world's spawn if
	 * the location is unknown
	 *
	 * @param player the player to teleport
	 */
	public void teleportPlayerBack(Player player);

	// ----------------------------------------------------------------------------------------
	// Main API methods
	// ----------------------------------------------------------------------------------------

	/**
	 * The name of the arena, as specified in the config.
	 */
	public String getName();

	/**
	 * Can players play this arena?
	 *
	 * @return is the arena enabled?
	 */
	public boolean isEnabled();

	/**
	 * Enabled arenas may be joined for playing, whereas disabled arenas may only be edited.
	 *
	 * @param enabled, toggle the arena being enabled
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Normally, when you stop or start the arena, the snapshots will automatically be restored.
	 * You can prevent this functionality here. It is typically used to safely stop the arena
	 * if we could not restore snapshot due to error, preventing infinite loop.
	 *
	 * @param restoreSnapshots
	 */
	public void setRestoreSnapshots(boolean restoreSnapshots);

	/**
	 * List of player names joined in the arena. This does not mean they are playing, it means
	 * they are registered in the arena, for example in the lobby.
	 */
	public Collection<Player> getPlayers();

	/**
	 * Check if a player is joined in the arena
	 *
	 * @param player the player to check
	 * @return
	 */
	public boolean isJoined(Player player);

	/**
	 * Check if a player is joined in the arena
	 *
	 * @param player the player to check
	 * @return
	 */
	public boolean isJoined(String playerName);

	/**
	 * The game state.
	 */
	public ArenaState getState();

	/**
	 * The arena's settings that user can alter.
	 */
	public ArenaSettings getSettings();

	/**
	 * The internal data from database.
	 */
	public ArenaData getData();

	/**
	 * The thing for sending messages.
	 */
	public ArenaMessenger getMessenger();

	/**
	 * Snapshot is all the blocks in the arena in a certain phase.
	 */
	public ArenaSnapshot getSnapshot();

	/**
	 * The setup manager, for example spawn points or supply points, etc.
	 */
	public Setup getSetup();

	/**
	 * Phases are levels within the arena
	 */
	public ArenaPhase getPhase();

	/**
	 * Has the stopping been initiated in the pipeline?
	 */
	public boolean isStopping();

	/**
	 * How much seconds is left before the finish?
	 *
	 * @return the remaining time, in seconds
	 */
	public int getRemainingSeconds();

	/**
	 * How many monsters that can be killed are in the arena?
	 *
	 * @return the alive monsters count
	 */
	public int getAliveMonsters();

	/**
	 * The plugin that owns this arena.
	 *
	 * @return the plugin
	 */
	public ArenaPlugin getPlugin();

	// ----------------------------------------------------------------------------------------
	// Automatic API methods - you should listen to events in bukkit and call them here manually
	// ----------------------------------------------------------------------------------------

	/**
	 * Called automatically when the arena is finished loading from its file on startup.
	 */
	public void onPostLoad();

	/**
	 * Called on a player vs. player fight
	 *
	 * @param event the event
	 * @param damager, the damager
	 * @param victim, the damaged
	 * @param damage, damage dealt
	 */
	public void onPlayerPvP(EntityDamageByEntityEvent event, Player damager, Player victim, double damage);

	/**
	 * Called when a player attacks a monster/animal
	 *
	 * @param damager the player who attacks
	 * @param victim, the damaged monster
	 * @param damage, damage dealth
	 */
	public void onPlayerPvE(Player damager, LivingEntity victim, double damage);

	/**
	 * Called when player is attacked by an entity
	 *
	 * @param event, the event
	 * @param player, the player
	 * @param source, the source
	 * @param damage, damage dealt
	 */
	public void onPlayerDamage(EntityDamageByEntityEvent event, Player player, Entity source, double damage);

	/**
	 * Called when player is damaged by block
	 *
	 * @param event the event
	 * @param player the player
	 * @param damage damage dealt
	 */
	public void onPlayerBlockDamage(EntityDamageByBlockEvent event, Player player, double damage);

	/**
	 * Called when a player is murdered
	 *
	 * @param player the player
	 * @param killer the killer
	 */
	public void onPlayerDeath(Player player, Player killer);

	/**
	 * Called when a player dies and we cannot detect the killer
	 *
	 * @param player the player
	 * @param cause the cause
	 */
	public void onPlayerDeath(Player player, DeathCause cause);

	/**
	 * Called on player block interaction
	 *
	 * @param player the player
	 * @param clickedBlock the clicked block
	 * @param hand the hand item
	 */
	public void onPlayerClick(Player player, Block clickedBlock, ItemStack hand);

	/**
	 * Called when a player clicks the air, useful for tools
	 *
	 * @param player, the player
	 * @param hand, the hand item
	 */
	public void onPlayerClickAir(Player player, ItemStack hand);

	/**
	 * Called when a player places a block
	 *
	 * @param event, the event
	 */
	public void onPlayerBlockPlace(BlockPlaceEvent event);

	/**
	 * Called when a player destroys a block
	 *
	 * @param event, the event
	 */
	public void onPlayerBlockBreak(BlockBreakEvent event);

	/**
	 * Called when a player is respawned
	 *
	 * @param event, the player
	 */
	public void onPlayerRespawn(PlayerRespawnEvent event);

	/**
	 * Called when a player picks up the exp item
	 *
	 * @param event, the player
	 * @param expItem, the exp item
	 */
	public void onPlayerPickupTag(PlayerPickupItemEvent event, ExpItem expItem);

	/**
	 * Called on entity spawn
	 *
	 * @param event, the event
	 */
	public void onEntitySpawn(EntitySpawnEvent event);

	/**
	 * Called on entity target
	 *
	 * @param event, the event
	 */
	public void onEntityTarget(EntityTargetEvent event);

	/**
	 * Called on entity death
	 *
	 * @param event, the event
	 */
	public void onEntityDeath(EntityDeathEvent event);

	/**
	 * Called when a new map snapshot is restored
	 *
	 * @param newState, the current arena snapshot stage
	 */
	public void onSnapshotUpdate(ArenaSnapshotStage newState);

	/**
	 * Called when a projectile is launched
	 *
	 * @param event, the event
	 */
	public void onProjectileLaunch(ProjectileLaunchEvent event);

	/**
	 * Called when projectile hits something
	 *
	 * @param event, the event
	 */
	public void onProjectileHit(ProjectileHitEvent event);
}
