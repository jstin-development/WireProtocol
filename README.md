# WireProtocol

A lightweight, high-performance Java library for low-level packet interception and protocol handling.

---

## Features

- Precise packet interception and manipulation
- Clean and extensible API design
- Minimal dependencies for easy integration
- Suitable for network tools Minecraft plugins

---

## Getting Started

### Installation

Add the latest release to your project dependencies (Maven/Gradle/etc.).


### Usage

```java
// Example usage here
Main class ->
    private WireProtocol wireProtocol;
    
    @Override
    public void onEnable() {
        this.wireProtocol = WireProtocolLibrary.get();
        // Register packet listener -> basePackage = where your packet listener are located at
        this.wireProtocol.getPacketEventManager().registerAllListeners("net.stella.dev.wireprotocol.example.packetcheck");
        // done -
    }
// Initialize and start listening to packets
