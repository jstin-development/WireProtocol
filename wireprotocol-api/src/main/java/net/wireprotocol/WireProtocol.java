package net.wireprotocol;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.Packet;
import net.wireprotocol.protocol.PacketEventManager;
import net.wireprotocol.protocol.PlayerProtocol;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * Singleton class managing packet listeners and dispatching received packets.
 */
public class WireProtocol {

    /** Registered packet listeners (thread-safe). */
    private final List<Consumer<Packet<?>>> listeners = new CopyOnWriteArrayList<>();

    /**
     * -- GETTER --
     *  Gets the PacketEventManager instance.
     *  You may want to expose this to register your own event listeners.
     */
    @Getter
    private final PacketEventManager packetEventManager = new PacketEventManager();

    private final PlayerProtocol playerProtocol = new PlayerProtocol();

    /** Volatile singleton instance for thread safety. */
    private static volatile WireProtocol instance;

    /** Private constructor for singleton pattern. */
    private WireProtocol() {}

    /**
     * Gets the singleton instance of WireProtocol.
     *
     * @return the singleton instance
     */
    public static WireProtocol getInstance() {
        if (instance == null) {
            synchronized (WireProtocol.class) {
                if (instance == null) {
                    instance = new WireProtocol();
                }
            }
        }
        return instance;
    }

    /**
     * Registers a packet listener which will be called when packets are received.
     *
     * @param listener a Consumer that accepts packets
     */
    public void registerListener(Consumer<Packet<?>> listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    /**
     * Dispatches the received packet to all registered listeners.
     *
     * @param packet the packet received
     */
    public void dispatchPacket(Packet<?> packet) {
        if (packet == null) return;

        for (Consumer<Packet<?>> listener : listeners) {
            try {
                listener.accept(packet);
            } catch (Exception exception) {
                System.err.println("Exception in packet listener: " + exception.getMessage());
                exception.fillInStackTrace();
            }
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
