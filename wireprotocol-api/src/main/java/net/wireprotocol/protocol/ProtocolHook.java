package net.wireprotocol.protocol;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import io.netty.channel.ChannelHandler;

/**
 * A hook for injecting and ejecting protocol handlers into a player's network channel.
 */
@AllArgsConstructor
public class ProtocolHook implements Injectable {

    /** The player whose channel this hook will manipulate */
    private final Player player;

    private static final NettyChannel INJECT_HANDLER = NettyChannel.WIREPROTOCOL_DECODER;
    private final PlayerProtocol playerProtocol = new PlayerProtocol();

    /**
     * Injects a custom ChannelHandler into the player's Netty pipeline.
     * Adds the handler after the WIREPROTOCOL_DECODER by default.
     */
    @Override
    public void inject(final ChannelHandler channelHandler) {
        var channel = playerProtocol.getChannel(player);
        if (channel == null || playerProtocol.existsNettyChannel(player, INJECT_HANDLER)) {
            // Already injected or channel doesn't exist
            return;
        }

        // Inject the handler after WIREPROTOCOL_DECODER
        playerProtocol.addNettyChannel(player, INJECT_HANDLER, NettyChannel.PACKET_SUPPRESSOR, channelHandler, PipelineState.AFTER);
    }

    /**
     * Removes the previously injected ChannelHandler from the player's Netty pipeline.
     */
    @Override
    public void eject() {
        if (!playerProtocol.existsNettyChannel(player, NettyChannel.PACKET_SUPPRESSOR)) {
            // Handler not present
            return;
        }

        playerProtocol.removeNettyChannel(player, NettyChannel.PACKET_SUPPRESSOR);
    }
}
