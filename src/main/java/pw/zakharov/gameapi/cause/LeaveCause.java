package pw.zakharov.gameapi.cause;

/**
 * Represents why a player left the arena
 */
public enum LeaveCause {

    /**
     * He got killed, etc
     */
    KILLED,

    /**
     * The player changed worlds and thus effectively escaped the arena and was kicked.
     */
    ESCAPED,

    /**
     * Left the game
     */
    DISCONNECT,

    /**
     * An error in the plugin has caused the player to be kicked for safety
     */
    ERROR,

    /**
     * Left by command or by clicking a sign
     */
    COMMAND,

    /**
     * Arena is finished
     */
    ARENA_END,

    /**
     * No class or team selected and could not chose a random class or team (no permission?)
     */
    NOT_READY,

    /**
     * The arena requires higher tier than all of the classes player disposes of.
     */
    NO_ENOUGH_CLASS,

    /**
     * Exited from the arena automatically, via AutoPlay for example
     */
    AUTO_MODE,
}
