package com.yaligaming.hebrewfix;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public final class HebrewFixConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
        .getConfigDir().resolve("hebrewfix.json");

    private static boolean enabled = true;

    private HebrewFixConfig() {}

    public static void load() {
        try {
            if (Files.exists(CONFIG_PATH)) {
                final String json = Files.readString(CONFIG_PATH);
                final ConfigData data = GSON.fromJson(json, ConfigData.class);
                if (data != null) {
                    enabled = data.enabled;
                }
            }
        } catch (final Exception e) {
            HebrewFixMod.LOGGER.warn("[HebrewFix] Failed to load config", e);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            final ConfigData data = new ConfigData();
            data.enabled = enabled;
            Files.writeString(CONFIG_PATH, GSON.toJson(data));
        } catch (final Exception e) {
            HebrewFixMod.LOGGER.warn("[HebrewFix] Failed to save config", e);
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(final boolean value) {
        enabled = value;
        save();
    }

    public static void toggle() {
        enabled = !enabled;
        save();
    }

    @SuppressWarnings("unused")
    private static class ConfigData {
        boolean enabled = true;
    }
}
