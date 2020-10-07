package pw.zakharov.gameapi;

import lombok.Data;

/**
 * Represents dropped exp item
 */
@Data
public class ExpItem {

    /**
     * How much experience should this item give?
     */
    private final int expGiven;
}