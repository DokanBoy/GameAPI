package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.Arena;
import pw.zakharov.gameapi.cause.LeaveCause;

/**
 * Triggered when a player has left the arena.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ArenaLeaveEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The arena
     */
    private final Arena arena;

    /**
     * The cause
     */
    private final LeaveCause cause;

    /**
     * The player
     */
    private final Player player;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}