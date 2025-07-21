package net.wireprotocol.protocol;

/**
 * Enum representing possible positions or actions
 * for inserting or modifying handlers in a Netty pipeline.
 * <p>
 * Used to specify where a new handler should be placed relative
 * to existing handlers in the pipeline.
 */
public enum PipelineState {

    /**
     * Insert the handler at the very start (head) of the pipeline.
     */
    FIRST,

    /**
     * Insert the handler at the very end (tail) of the pipeline.
     */
    LAST,

    /**
     * Insert the handler before a specified existing handler.
     */
    BEFORE,

    /**
     * Insert the handler after a specified existing handler.
     */
    AFTER,

    /**
     * Replace an existing handler with a new handler.
     */
    REPLACE,
}


