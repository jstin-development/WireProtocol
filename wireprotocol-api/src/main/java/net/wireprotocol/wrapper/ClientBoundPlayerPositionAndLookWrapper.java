package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public class ClientBoundPlayerPositionAndLookWrapper implements PacketWrapper {

    private final PacketPlayInFlying.PacketPlayInPositionLook packet;

    public ClientBoundPlayerPositionAndLookWrapper(PacketPlayInFlying.PacketPlayInPositionLook packet) {
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
     * Gets the yaw (horizontal rotation) sent by the client.
     */
    public float getYaw() {
        return packet.d();
    }

    /**
     * Gets the pitch (vertical rotation) sent by the client.
     */
    public float getPitch() {
        return packet.e();
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
    public PacketPlayInFlying.PacketPlayInPositionLook getHandle() {
        return packet;
    }
}
