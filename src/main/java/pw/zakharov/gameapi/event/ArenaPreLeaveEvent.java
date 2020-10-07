package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.Arena;
import pw.zakharov.gameapi.cause.LeaveCause;

/**
 * Triggered when a player attempts to leave the arena.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ArenaPreLeaveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The arena
     */
    private final Arena arena;

    /**
     * Why is he leaving
     */
    private final LeaveCause cause;

    /**
     * The player
     */
    private final Player player;

    /**
     * Prevent that he leaves the arena?
     */
    private boolean cancelled = false;

    /**
     * Do not broadcast that he left?
     */
    private boolean silent = false;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}