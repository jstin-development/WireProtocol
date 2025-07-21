package net.wireprotocol.protocol;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Enum representing the names of Netty channel handlers
 * used in the wireprotocol.WireProtocol library for packet interception.
 *
 * Each constant holds the handler name used when injecting
 * or managing Netty pipeline handlers.
 */
@Getter
@Accessors(fluent = true)
public enum NettyChannel {

    /** Standard Netty decoder handler name */
    DECODER("decoder"),

    /** Custom wireprotocol.WireProtocol decoder handler name */
    WIREPROTOCOL_DECODER("wireprotocol_decoder"),

    /** Standard Netty splitter handler name */
    SPLITTER("splitter"),

    /** Custom wireprotocol.WireProtocol splitter handler name */
    WIREPROTOCOL_SPLITTER("wireprotocol_splitter"),

    /** Standard Netty decompress handler name */
    DECOMPRESS("decompress"),

    /** The standard packet handler in Netty pipeline */
    NEW("packet_handler"),

    /** Custom wireprotocol.WireProtocol decompress handler name */
    WIREPROTOCOL_DECOMPRESS("wireprotocol_decompress"),

    /** Handler for closed channel events */
    CHANNEL_CLOSED_HANDLER("ChannelClosedHandler"),

    /** Handler that suppresses certain packets */
    PACKET_SUPPRESSOR("packet_suppressor");

    /**
     * The name of the handler in the Netty pipeline.
     */
    private String handlerName;

    /**
     * Constructs a NettyChannel enum constant with the specified handler name.
     *
     * @param handlerName the name of the Netty handler
     */
    NettyChannel(final String handlerName) {
        this.handlerName = handlerName;
    }

    /**
     * Allows overriding the handler name for this enum constant.
     * Useful for customizing handler names dynamically.
     *
     * @param customName new handler name to set
     * @return this enum constant with updated handler name
     */
    public NettyChannel withHandlerName(String customName) {
        this.handlerName = customName;
        return this;
    }

    /**
     * Returns the handler name as a string.
     *
     * @return the handler name
     */
    @Override
    public String toString() {
        return handlerName;
    }
}
