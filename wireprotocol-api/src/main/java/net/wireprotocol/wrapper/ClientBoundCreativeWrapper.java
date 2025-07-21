package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot;

public class ClientBoundCreativeWrapper implements PacketWrapper {

    private final PacketPlayInSetCreativeSlot packet;

    public ClientBoundCreativeWrapper(PacketPlayInSetCreativeSlot packet) {
        this.packet = packet;
    }

    /**
     * Gets the inventory slot that was set.
     */
    public int getSlot() {
        return packet.a(); // Corresponds to the inventory slot
    }

    /**
     * Gets the item stack that was set in the creative slot.
     */
    public ItemStack getItemStack() {
        return packet.getItemStack(); // Corresponds to the item stack
    }

    /**
     * Returns the raw NMS packet instance.
     */
    @Override
    public PacketPlayInSetCreativeSlot getHandle() {
        return packet;
    }
}
