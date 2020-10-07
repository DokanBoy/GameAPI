package pw.zakharov.gameapi.cause;

/**
 * Represents the join cause for a player in the arena
 */
public enum JoinCause {

    /**
     * Player has been joined automatically, via AutoPlay for example
     */
    AUTO_JOIN,

    /**
     * Player has joined via command
     */
    COMMAND,

    /**
     * Player has joined via clicking a regular sign
     */
    SIGN
}
