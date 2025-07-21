package net.wireprotocol.wrapper;

import net.minecraft.server.v1_8_R3.PacketPlayInAbilities;

public class ClientBoundAbilitiesWrapper implements PacketWrapper {

    private final PacketPlayInAbilities packet;

    public ClientBoundAbilitiesWrapper(PacketPlayInAbilities packet) {
        this.packet = packet;
    }

    public boolean isInvulnerable() {
        return packet.a();
    }

    public boolean isFlying() {
        return packet.isFlying();
    }

    public boolean canFly() {
        return packet.c();
    }

    public boolean canInstantlyBuild() {
        return packet.d();
    }

    @Override
    public Object getHandle() {
        return packet;
    }
}
