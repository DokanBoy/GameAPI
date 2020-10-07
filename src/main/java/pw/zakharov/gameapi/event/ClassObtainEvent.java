package pw.zakharov.gameapi.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pw.zakharov.gameapi.ArenaClass;

/**
 * Triggered before the a player attempts to receive a class.
 */
@Getter
@RequiredArgsConstructor
public final class ClassObtainEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    /**
     * The player
     */
    private final Player player;

    /**
     * The class
     */
    private final ArenaClass arenaClass;

    /**
     * The tier
     */
    private final int tier;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}