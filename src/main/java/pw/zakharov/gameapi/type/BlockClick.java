package pw.zakharov.gameapi.type;

import org.bukkit.event.block.Action;

import java.util.Objects;

/**
 * Used when player has clicked on a block.
 */
public enum BlockClick {

    /**
     * The right block click
     */
    RIGHT_CLICK,

    /**
     * The left block click
     */
    LEFT_CLICK;

    public static BlockClick fromAction(Action action) {
        final BlockClick click = BlockClick.valueOf(action.toString().replace("_BLOCK", ""));
        Objects.requireNonNull(click, "Report / Unsupported click type from " + action);

        return click;
    }
}
