package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

public class ClientBoundPlayerLookWrapper implements PacketWrapper {

    private final PacketPlayInFlying.PacketPlayInLook packet;

    public ClientBoundPlayerLookWrapper(PacketPlayInFlying.PacketPlayInLook packet) {
        this.packet = packet;
    }

    /**
     * Gets the yaw (horizontal rotation) sent by the client.
     */
    public double getYaw() {
        return packet.a();
    }

    /**
     * Gets the pitch (vertical rotation) sent by the client.
     */
    public double getPitch() {
        return packet.b();
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
    public PacketPlayInFlying.PacketPlayInLook getHandle() {
        return packet;
    }
}
