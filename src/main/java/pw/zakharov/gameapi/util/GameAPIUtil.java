package pw.zakharov.gameapi.util;

import org.bukkit.ChatColor;

/**
 * Misc utils
 */
public final class GameAPIUtil {

    /**
     * Colorizes the messages using the & letters.
     *
     * @param message the message
     * @return the colorized message.
     */
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Traces method calls from the invocation place.
     *
     * @param message the title of the message for clarity
     */
    public static void trace(String message) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(message);
        System.out.println();

        for (final StackTraceElement e : new Throwable().getStackTrace())
            if (e.toString().contains("me."))
                System.out.println("    at " + e.toString());

        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}
