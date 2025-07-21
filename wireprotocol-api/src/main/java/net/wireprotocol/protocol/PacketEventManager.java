package net.wireprotocol.protocol;

import net.wireprotocol.event.PacketEvent;
import org.reflections.Reflections;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class PacketEventManager {

    private final List<PacketListener> listeners = new ArrayList<>();

    public void registerAllListeners(String basePackage) {
        var reflections = new Reflections(basePackage);
        Set<Class<? extends PacketListener>> classes = reflections.getSubTypesOf(PacketListener.class);

        for (Class<? extends PacketListener> clazz : classes) {
            try {
                var listener = clazz.getDeclaredConstructor().newInstance();
                this.registerListener(listener);
            } catch (Exception exception) {
                throw new IllegalArgumentException("Could not register " + listeners + " exception: " + exception.getMessage());
            }
        }
    }

    public void registerListener(PacketListener listener) {
        listeners.add(listener);
    }

    public void call(PacketEvent event) {
        for (PacketListener listener : listeners) {
            listener.onPacket(event);
            if (event.isCancelled()) break;  // stop processing if cancelled
        }
    }
}
