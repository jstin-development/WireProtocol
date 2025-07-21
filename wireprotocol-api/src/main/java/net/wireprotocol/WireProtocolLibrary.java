package net.wireprotocol;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WireProtocolLibrary {

    public WireProtocol get() {
        return WireProtocol.getInstance();
    }

}