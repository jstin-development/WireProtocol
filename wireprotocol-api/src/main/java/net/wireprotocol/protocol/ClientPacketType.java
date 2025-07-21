package net.wireprotocol.protocol;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.*;

@Getter
public enum ClientPacketType {

    KEEP_ALIVE(PacketPlayInKeepAlive.class),
    CHAT(PacketPlayInChat.class),
    USE_ENTITY(PacketPlayInUseEntity.class),
    PLAYER(PacketPlayInFlying.class),
    PLAYER_POSITION(PacketPlayInFlying.PacketPlayInPosition.class),
    PLAYER_LOOK(PacketPlayInFlying.PacketPlayInLook.class),
    PLAYER_POSITION_AND_LOOK(PacketPlayInFlying.PacketPlayInPositionLook.class),
    BLOCK_DIG(PacketPlayInBlockDig.class),
    BLOCK_PLACE(PacketPlayInBlockPlace.class),
    ABILITIES(PacketPlayInAbilities.class),
    TAB_COMPLETE(PacketPlayInTabComplete.class),
    HELD_ITEM_SLOT(PacketPlayInHeldItemSlot.class),
    ARM_ANIMATION(PacketPlayInArmAnimation.class),
    ENTITY_ACTION(PacketPlayInEntityAction.class),
    STEER_VEHICLE(PacketPlayInSteerVehicle.class),
    CLOSE_WINDOW(PacketPlayInCloseWindow.class),
    CLICK_WINDOW(PacketPlayInWindowClick.class),
    ENCHANT_ITEM(PacketPlayInEnchantItem.class),
    SET_CREATIVE_SLOT(PacketPlayInSetCreativeSlot.class),
    PLUGIN_MESSAGE(PacketPlayInCustomPayload.class),
    TRANSACTION(PacketPlayInTransaction.class),
    UPDATE_SIGN(PacketPlayInUpdateSign.class),
    ANIMATION(PacketPlayOutAnimation.class),
    SPECTATE(PacketPlayInSpectate.class),
    RESOURCE_PACK_STATUS(PacketPlayInResourcePackStatus.class),

    UNKNOWN(null);

    private final Class<? extends Packet<?>> packetClass;

    ClientPacketType(Class<? extends Packet<?>> packetClass) {
        this.packetClass = packetClass;
    }

    public static ClientPacketType fromPacket(Packet<?> packet) {
        if (packet == null) return UNKNOWN;
        for (ClientPacketType type : values()) {
            if (type.packetClass != null && type.packetClass.isInstance(packet)) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
