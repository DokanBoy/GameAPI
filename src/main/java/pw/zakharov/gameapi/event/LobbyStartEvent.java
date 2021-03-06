package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.Arena;

/**
 * Triggered when the lobby starts when the first player joins.
 */
@Getter
@Setter
public final class LobbyStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The arena
     */
    private final Arena arena;

    /**
     * How many seconds should the lobby vary?
     */
    private int countdown;

    public LobbyStartEvent(Arena arena) {
        this.arena = arena;
        this.countdown = arena.getSettings().getLobbyDurationSeconds();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}