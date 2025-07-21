package net.wireprotocol.protocol;

import io.netty.channel.ChannelHandler;

public interface Injectable {
    void inject();
    void eject();
}
