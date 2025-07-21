package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;

public class ClientBoundWindowClickWrapper implements PacketWrapper {

    private final PacketPlayInWindowClick packet;

    public ClientBoundWindowClickWrapper(PacketPlayInWindowClick packet) {
        this.packet = packet;
    }

    /**
     * Gets the window ID.
     */
    public int getWindowId() {
        return packet.a(); // formerly field "a"
    }

    /**
     * Gets the slot number that was clicked.
     */
    public int getSlot() {
        return packet.b(); // field "slot"
    }

    /**
     * Gets the mouse button used.
     */
    public int getButton() {
        return packet.c(); // field "button"
    }

    /**
     * Gets the action number used for transaction validation.
     */
    public short getActionNumber() {
        return packet.d(); // field "d"
    }

    /**
     * Gets the item stack clicked or used.
     */
    public ItemStack getItemStack() {
        return packet.e(); // field "item"
    }

    /**
     * Gets the mode (e.g., 0 = normal click, 1 = shift click).
     */
    public int getClickMode() {
        return packet.f(); // field "shift"
    }

    /**
     * Exposes raw access to the original packet.
     */
    public PacketPlayInWindowClick getHandle() {
        return packet;
    }
}
