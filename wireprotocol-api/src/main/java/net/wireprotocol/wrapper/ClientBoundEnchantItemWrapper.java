package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInEnchantItem;

public class ClientBoundEnchantItemWrapper implements PacketWrapper {

    private final PacketPlayInEnchantItem packet;

    public ClientBoundEnchantItemWrapper(PacketPlayInEnchantItem packet) {
        this.packet = packet;
    }

    /**
     * Gets the window ID where the enchant action occurred.
     */
    public int getWindowId() {
        return packet.a();
    }

    /**
     * Gets the enchantment action ID (button clicked).
     */
    public int getEnchantId() {
        return packet.b();
    }

    @Override
    public PacketPlayInEnchantItem getHandle() {
        return packet;
    }
}
