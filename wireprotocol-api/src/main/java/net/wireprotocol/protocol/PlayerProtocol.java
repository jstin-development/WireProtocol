package net.wireprotocol.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import lombok.NonNull;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Utility class for managing Netty channels
 * associated with a Bukkit Player's network connection.
 * <p>
 * Provides methods to add, remove, replace, query, and manipulate
 * Netty pipeline handlers for individual players.
 */
public class PlayerProtocol {

    // Cache of reflection fields and methods to reduce overhead on repeated calls
    private final Map<Class<?>, Field> fieldCache = new ConcurrentHashMap<>();
    private final Map<Class<?>, Method> methodCache = new ConcurrentHashMap<>();

    /**
     * Retrieves the Netty Channel for the given player.
     *
     * @param player the Bukkit player whose channel is retrieved
     * @return the Netty Channel instance for the player's network connection, or null if unavailable
     */
    public Channel getChannel(final @NonNull Player player) {
        try {
            var craftPlayer = (CraftPlayer) player;
            return craftPlayer.getHandle().playerConnection.networkManager.channel;
        } catch (Exception exception) {
            // Optionally log exception here
            return null;
        }
    }

    /**
     * Removes a Netty handler from the player's pipeline if it exists.
     *
     * @param player      the Bukkit player whose pipeline is modified
     * @param channelName the NettyChannel enum representing the handler to remove
     */
    public void removeNettyChannel(@NonNull Player player, @NonNull NettyChannel channelName) {
        var channel = getChannel(player);
        if (channel != null && channel.pipeline().get(channelName.handlerName()) != null) {
            channel.pipeline().remove(channelName.handlerName());
        }
    }

    /**
     * Adds a Netty handler to the player's pipeline.
     *
     * @param player        the Bukkit player
     * @param relativeTo    the NettyChannel to position relative to
     * @param newChannel    the NettyChannel representing the new handler
     * @param pipelineState the pipeline state (FIRST, LAST, BEFORE, AFTER, REPLACE)
     */
    public void addNettyChannel(@NonNull Player player,
                                @NonNull NettyChannel relativeTo,
                                @NonNull NettyChannel newChannel,
                                @NonNull PipelineState pipelineState) {
        var channel = getChannel(player);
        if (channel == null) return;
        var handler = new PacketDecoder(player);

        try {
            switch (pipelineState) {
                case AFTER -> channel.pipeline().addAfter(relativeTo.handlerName(), newChannel.handlerName(), handler);
                case BEFORE -> channel.pipeline().addBefore(relativeTo.handlerName(), newChannel.handlerName(), handler);
                case LAST -> channel.pipeline().addLast(newChannel.handlerName(), handler);
                case FIRST -> channel.pipeline().addFirst(newChannel.handlerName(), handler);
                case REPLACE -> {
                    if (channel.pipeline().get(relativeTo.handlerName()) != null) {
                        channel.pipeline().replace(relativeTo.handlerName(), newChannel.handlerName(), handler);
                    } else {
                        channel.pipeline().addLast(newChannel.handlerName(), handler);
                    }
                }
            }
        } catch (IllegalArgumentException exception) {
            String message = String.format("Failed to add handler '%s' relative to '%s': %s",
                    newChannel.handlerName(), relativeTo.handlerName(), exception.getMessage());
            throw new IllegalArgumentException(message, exception);
        }
    }

    /**
     * Closes the Netty Channel for the player if it is open.
     *
     * @param player the Bukkit player whose channel will be closed
     */
    public void closeChannel(@NonNull Player player) {
        var channel = getChannel(player);
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
    }

    /**
     * Checks if a Netty handler exists in the player's pipeline.
     *
     * @param player      the Bukkit player
     * @param channelName the NettyChannel enum representing the handler
     * @return true if handler exists, false otherwise
     */
    public boolean existsNettyChannel(@NonNull Player player, @NonNull NettyChannel channelName) {
        var channel = getChannel(player);
        return channel != null && channel.pipeline().get(channelName.handlerName()) != null;
    }

    /**
     * Gets the actual handler name in the pipeline matching the provided NettyChannel.
     *
     * @param player      the Bukkit player
     * @param channelName the NettyChannel to look for
     * @return the handler name if found, null otherwise
     */
    public String getNettyChannelName(@NonNull Player player, @NonNull NettyChannel channelName) {
        var channel = getChannel(player);
        if (channel == null) return null;

        return channel.pipeline().names().stream()
                .filter(name -> name.equals(channelName.handlerName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns all handler names in the player's Netty pipeline.
     *
     * @param player the Bukkit player
     * @return array of handler names, or empty array if none
     */
    public String[] getNettyChannelNames(@NonNull Player player) {
        var channel = getChannel(player);
        if (channel == null) return new String[0];
        return channel.pipeline().names().toArray(new String[0]);
    }

    /**
     * Uses reflection to get the value of a private field from an instance.
     *
     * @param instance  the object instance
     * @param fieldName the private field name
     * @return the field's value
     * @throws Exception if reflection fails
     */
    private Object getField(Object instance, String fieldName) throws Exception {
        var clazz = instance.getClass();
        var field = fieldCache.computeIfAbsent(clazz, key -> {
            try {
                var f = key.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } catch (NoSuchFieldException | SecurityException e) {
                return null;
            }
        });
        if (field == null) throw new NoSuchFieldException("Field " + fieldName + " not found in class " + clazz.getName());
        return field.get(instance);
    }

    /**
     * Uses reflection to invoke a method with no parameters on an instance.
     *
     * @param instance   the object instance
     * @param methodName the method name
     * @return the method's return value
     * @throws Exception if reflection fails
     */
    private Object invokeMethod(Object instance, String methodName) throws Exception {
        var clazz = instance.getClass();
        var method = methodCache.computeIfAbsent(clazz, key -> {
            try {
                var keyMethod = key.getMethod(methodName);
                keyMethod.setAccessible(true);
                return keyMethod;
            } catch (NoSuchMethodException | SecurityException e) {
                return null;
            }
        });
        if (method == null) throw new NoSuchMethodException("Method " + methodName + " not found in class " + clazz.getName());
        return method.invoke(instance);
    }
}
