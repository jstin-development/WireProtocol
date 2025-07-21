package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;

public class ClientBoundBlockPlaceWrapper implements PacketWrapper {

    private final PacketPlayInBlockPlace packet;

    public ClientBoundBlockPlaceWrapper(PacketPlayInBlockPlace packet) {
        this.packet = packet;
    }

    /**
     * Gets the block position where the block is placed.
     */
    public BlockPosition getPosition() {
        return packet.a();
    }

    public int getFace() {
        return packet.getFace();
    }

    /**
     * Gets the item stack placed.
     */
    public ItemStack getItemStack() {
        return packet.getItemStack();
    }

    /**
     * Gets the cursor Y position (0.0 to 1.0).
     */
    public float getCursorY() {
        return packet.d();
    }

    /**
     * Gets the cursor Z position (0.0 to 1.0).
     */
    public float getCursorZ() {
        return packet.e();
    }

    /**
     * Returns the underlying NMS packet.
     */
    @Override
    public PacketPlayInBlockPlace getHandle() {
        return packet;
    }
}
