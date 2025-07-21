package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public class ClientBoundPlayerPositionWrapper implements PacketWrapper {

    private final PacketPlayInFlying.PacketPlayInPosition packet;

    public ClientBoundPlayerPositionWrapper(PacketPlayInFlying.PacketPlayInPosition packet) {
        this.packet = packet;
    }

    /**
     * Gets the X position sent by the client.
     */
    public double getX() {
        return packet.a();
    }

    /**
     * Gets the Y position sent by the client.
     */
    public double getY() {
        return packet.b();
    }

    /**
     * Gets the Z position sent by the client.
     */
    public double getZ() {
        return packet.c();
    }

    /**
     * Returns whether the player is on ground.
     */
    public boolean isOnGround() {
        return packet.f();
    }

    /**
     * Returns the underlying NMS packet.
     */
    @Override
    public PacketPlayInFlying.PacketPlayInPosition getHandle() {
        return packet;
    }
}
