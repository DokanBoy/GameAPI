package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.Arena;

/**
 * Triggered when the player is teleported to the arena lobby.
 */
@Getter
@Setter
public final class ArenaLobbyTeleportEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The player
     */
    private final Player player;

    /**
     * The arena
     */
    private final Arena arena;

    /**
     * The location where to teleport the player, can be changed
     */
    private Location location;

    /**
     * Should we do not teleport?
     */
    private boolean cancelled;

    public ArenaLobbyTeleportEvent(Player player, Arena arena, Location location) {
        this.player = player;
        this.arena = arena;
        this.location = location;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}