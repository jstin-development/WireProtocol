package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayInUpdateSign;
import net.minecraft.server.v1_8_R3.BlockPosition;

public class ClientBoundUpdateSignWrapper implements PacketWrapper {

    private final PacketPlayInUpdateSign packet;

    public ClientBoundUpdateSignWrapper(PacketPlayInUpdateSign packet) {
        this.packet = packet;
    }

    /**
     * Gets the block position of the sign being updated.
     */
    public BlockPosition getPosition() {
        return packet.a();
    }

    /**
     * Gets the lines of text on the sign.
     */
    public IChatBaseComponent[] getLines() {
        return packet.b();
    }

    @Override
    public PacketPlayInUpdateSign getHandle() {
        return packet;
    }
}
