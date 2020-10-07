package pw.zakharov.gameapi.impl;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.zakharov.gameapi.Arena;
import pw.zakharov.gameapi.ArenaMessenger;
import pw.zakharov.gameapi.type.ArenaSound;
import pw.zakharov.gameapi.type.MessengerTarget;
import pw.zakharov.gameapi.util.LegacyAPIUtil;

import java.util.Collection;

/**
 * Represents a simple messenger that handles sending messages
 * to players inside of the arena.
 */
public final class BasicMessenger implements ArenaMessenger {

    private final Arena arena;
    /**
     * To whom should we send the messages?
     */
    @Getter
    @Setter
    private MessengerTarget target = MessengerTarget.ARENA;

    /**
     * Create a new messenger
     *
     * @param arena the arena for this messenger
     */
    public BasicMessenger(Arena arena) {
        this.arena = arena;
    }

    /**
     * Only tells the directed players, with the player and other variables
     *
     * @param player  the player
     * @param message the message
     */
    @Override
    public void tell(Player player, String message) {
        player.sendMessage(replaceVariables(message.replace("{player}", player.getName())));
    }

    /**
     * Tells all players in the arena, replaces variables
     *
     * @param message the message
     */
    @Override
    public void broadcast(String message) {
        message = replaceVariables(message);

        for (final CommandSender sender : getRecipients())
            sender.sendMessage(message.replace("{player}", sender.getName()));
    }

    /**
     * Broadcasts a message on the boss bar
     *
     * @param message the message
     */
    @Override
    public void broadcastBar(String message) {
        final BaseComponent[] comp = TextComponent.fromLegacyText(replaceVariables(message));

        try {
            for (final Player player : getRecipients())
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, comp);

        } catch (final NoSuchMethodError er) {
            broadcast(message);
        }
    }

    /**
     * Plays a sound globally to all players
     *
     * @param sound sound
     * @param pitch pitch
     */
    @Override
    public void playSound(ArenaSound sound, float pitch) {
        for (final Player pl : getRecipients())
            playSound(pl, sound, pitch);
    }

    /**
     * Plays a sound to a player
     *
     * @param player the player
     * @param sound  sound
     * @param pitch  pitch
     */
    @Override
    public void playSound(Player player, ArenaSound sound, float pitch) {
        player.playSound(player.getLocation(), Sound.valueOf(sound.toString()), 1F, pitch);
    }

    /**
     * Translates all arena-relevant variables before the message is sent
     * for example {arena} in the {@link #arena} name
     *
     * @param message message to translate
     * @return the replaced message
     */
    @Override
    public String replaceVariables(String message) {
        return ChatColor.translateAlternateColorCodes('&', message
                .replace("{arena}", arena.getName())
                .replace("{state}", arena.getState().toString().toLowerCase())
                .replace("{phase}", arena.getPhase().getCurrent() + "")
                .replace("{players}", getRecipients().size() + "")
                .replace("{maxPlayers}", arena.getSettings().getMaximumPlayers() + "")
                .replace("{minPlayers}", arena.getSettings().getMinimumPlayers() + ""));
    }

    // Get all recipients eligible for messaging
    private Collection<? extends Player> getRecipients() {
        switch (target) {
            case ARENA:
                return arena.getPlayers();

            case WORLD:
                if (arena.getData().getRegion().getWorld() != null)
                    return arena.getData().getRegion().getWorld().getPlayers();

                throw new RuntimeException("Attempted to get recipients for arena " + arena.getName() + " that has not world/region yet!");

            case SERVER:
                return LegacyAPIUtil.getOnlinePlayers();

            default:
                throw new RuntimeException("Unhandled target " + target);
        }
    }
}
