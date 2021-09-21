package com.strixmc.powerups.hooks;

import com.strixmc.powerups.PowerUps;
import com.strixmc.powerups.powerup.PowerUp;
import com.strixmc.powerups.services.PluginService;
import com.strixmc.powerups.utils.commons.service.Initializer;
import lombok.Getter;
import lombok.Setter;

public class HookManager implements Initializer {

    @Getter
    @Setter
    private boolean initialized;

    @Getter
    private PlaceholderAPIHook papiHook;
    @Getter
    private HolographicDisplaysHook holoHook;

    private final PowerUps main;

    public HookManager(PluginService pluginService) {
        this.main = pluginService.getMain();
        this.initialized = false;
        start();
    }

    @Override
    public void start() {
        setInitialized(true);

        this.papiHook = new PlaceholderAPIHook(main);
        this.holoHook = new HolographicDisplaysHook(main);
    }

    @Override
    public void finish() {
        if (!initialized) return;

        this.papiHook.finish();
        this.holoHook.finish();
    }
}
