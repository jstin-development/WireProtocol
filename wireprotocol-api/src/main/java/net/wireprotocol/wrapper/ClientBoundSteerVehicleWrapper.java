package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInSteerVehicle;

public class ClientBoundSteerVehicleWrapper implements PacketWrapper {

    private final PacketPlayInSteerVehicle packet;

    public ClientBoundSteerVehicleWrapper(PacketPlayInSteerVehicle packet) {
        this.packet = packet;
    }

    /**
     * Gets the sideways movement input (-1.0 to 1.0).
     */
    public float getSideways() {
        return packet.a();
    }

    /**
     * Gets the forward movement input (-1.0 to 1.0).
     */
    public float getForward() {
        return packet.b();
    }

    /**
     * Returns whether the jump key is pressed.
     */
    public boolean isJumping() {
        return packet.c();
    }

    /**
     * Returns whether the unmount key is pressed.
     */
    public boolean isUnmounting() {
        return packet.d();
    }

    @Override
    public PacketPlayInSteerVehicle getHandle() {
        return packet;
    }
}
