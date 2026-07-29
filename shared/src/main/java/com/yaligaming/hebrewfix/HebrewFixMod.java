package com.yaligaming.hebrewfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class HebrewFixMod implements ClientModInitializer {

    public static final String MOD_ID = "hebrewfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        HebrewFixConfig.load();
        LOGGER.info("[HebrewFix] Initialized. Enabled: {}",
            HebrewFixConfig.isEnabled());
    }
}
