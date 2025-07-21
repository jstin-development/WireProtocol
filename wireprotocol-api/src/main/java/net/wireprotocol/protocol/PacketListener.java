package net.wireprotocol.protocol;

import net.wireprotocol.event.PacketEvent;

@FunctionalInterface
public interface PacketListener {
    void onPacket(PacketEvent event);
}