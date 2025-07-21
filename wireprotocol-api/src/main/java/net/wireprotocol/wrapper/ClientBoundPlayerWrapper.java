package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public class ClientBoundPlayerWrapper implements PacketWrapper{

    private final PacketPlayInFlying packet;

    public ClientBoundPlayerWrapper(PacketPlayInFlying packet) {
        this.packet = packet;
    }

    /**
     * Returns if this packet contains position data.
     */
    public boolean hasPosition() {
        return packet.g();  // 'g()' usually means hasPosition
    }

    /**
     * Returns if this packet contains look (rotation) data.
     */
    public boolean hasLook() {
        return packet.h();  // 'h()' usually means hasLook
    }

    /**
     * Get player's X position.
     */
    public double getX() {
        return packet.a();
    }

    /**
     * Get player's Y position.
     */
    public double getY() {
        return packet.b();
    }

    /**
     * Get player's Z position.
     */
    public double getZ() {
        return packet.c();
    }

    /**
     * Get player's yaw (rotation around vertical axis).
     */
    public float getYaw() {
        return packet.d();
    }

    /**
     * Get player's pitch (rotation up/down).
     */
    public float getPitch() {
        return packet.e();
    }

    /**
     * Check if the player is on ground.
     */
    public boolean isOnGround() {
        return packet.f();
    }

    /**
     * Gets the raw packet instance.
     */
    public PacketPlayInFlying getHandle() {
        return packet;
    }
}
