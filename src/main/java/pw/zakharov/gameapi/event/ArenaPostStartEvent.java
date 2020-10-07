package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.Arena;

/**
 * Triggered when the arena starts after the lobby wait time has finished.
 */
@Getter
@RequiredArgsConstructor
public final class ArenaPostStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The arena
     */
    private final Arena arena;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}