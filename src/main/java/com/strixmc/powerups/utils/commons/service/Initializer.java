package com.strixmc.powerups.utils.commons.service;

public interface Initializer {

    boolean isInitialized();

    void setInitialized(boolean value);

    /**
     * Start method.
     */
    void start();

    /**
     * Implement inf needed.
     */
    default void finish() {

    }

}
