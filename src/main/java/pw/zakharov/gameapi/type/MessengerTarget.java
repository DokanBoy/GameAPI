package pw.zakharov.gameapi.type;

import pw.zakharov.gameapi.ArenaMessenger;

/**
 * Represents to whom we should send messages from {@link ArenaMessenger}
 */
public enum MessengerTarget {

    /**
     * The default
     * <p>
     * Players in the arena
     */
    ARENA,

    /**
     * Players in the same world as the arena is within, used by world automatic mode
     */
    WORLD,

    /**
     * Everyone on the server, used by server automatic mode
     */
    SERVER
}
