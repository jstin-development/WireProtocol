package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.*;

public class ClientBoundUseEntityWrapper implements PacketWrapper {

    private final PacketPlayInUseEntity packet;

    public ClientBoundUseEntityWrapper(PacketPlayInUseEntity packet) {
        this.packet = packet;
    }

    /**
     * Gets the raw entity ID that is being interacted with.
     */
    public int getEntityId() {
        // Accessing the private field directly is not possible; you'd need reflection if this was truly private.
        // However, you can use the method: a(World) to resolve the entity, or track the ID in context.
        // Since we only have method a(World), and no direct getter for ID, you may need to adapt usage.
        // You might want to store the entity ID manually when decoding.
        throw new UnsupportedOperationException("Entity ID not directly accessible without World context.");
    }

    /**
     * Gets the interaction action (INTERACT, ATTACK, INTERACT_AT).
     */
    public PacketPlayInUseEntity.EnumEntityUseAction getAction() {
        return packet.a(); // action is returned by method `a()`
    }

    /**
     * Gets the interaction point (if INTERACT_AT is used).
     */
    public Vec3D getInteractionPoint() {
        return packet.b(); // Returns Vec3D if action == INTERACT_AT
    }

    /**
     * Returns the raw packet.
     */
    @Override
    public PacketPlayInUseEntity getHandle() {
        return packet;
    }
}
