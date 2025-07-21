package net.wireprotocol;

import net.minecraft.server.v1_8_R3.Packet;
import net.wireprotocol.protocol.PlayerProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Singleton class managing packet listeners and dispatching received packets.
 */
public class WireProtocol {

    /** Registered packet listeners. */
    private final List<Consumer<Packet<?>>> listeners = new ArrayList<>();

    /** PlayerProtocol instance for handling player channel interactions. */
    private final PlayerProtocol playerProtocol = new PlayerProtocol();

    /** Singleton instance of wireprotocol.WireProtocol. */
    private static WireProtocol instance;

    /** Private constructor for singleton pattern. */
    private WireProtocol() {}

    /**
     * Gets the singleton instance of wireprotocol.WireProtocol.
     *
     * @return the singleton instance
     */
    public static WireProtocol getInstance() {
        if (instance == null) {
            instance = new WireProtocol();
        }
        return instance;
    }

    /**
     * Registers a packet listener which will be called when packets are received.
     *
     * @param listener a Consumer that accepts packets
     */
    public void registerListener(Consumer<Packet<?>> listener) {
        listeners.add(listener);
    }

    /**
     * Called internally when a packet is received, dispatching to all registered listeners.
     *
     * @param packet the packet received
     */
    public void onPacketReceived(Packet<?> packet) {
        for (var listener : listeners) {
            listener.accept(packet);
        }
    }

    /**
     * Gets the PlayerProtocol instance.
     *
     * @return PlayerProtocol instance
     */
    public PlayerProtocol playerProtocol() {
        return playerProtocol;
    }
}
