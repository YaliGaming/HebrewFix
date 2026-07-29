package com.yaligaming.hebrewfix;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(final Screen parent) {
        return new ConfigScreen(parent);
    }

    private static final class ConfigScreen extends Screen {

        private final Screen parent;

        ConfigScreen(final Screen parent) {
            super(Component.literal("HebrewFix"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            final boolean enabled = HebrewFixConfig.isEnabled();

            addRenderableWidget(Button.builder(
                Component.literal("Status: ")
                    .append(Component.literal(enabled ? "Enabled" : "Disabled")
                        .withColor(
                            enabled ? 0x55FF55 : 0xFF5555)),
                btn -> {
                    HebrewFixConfig.toggle();
                    final boolean now = HebrewFixConfig.isEnabled();
                    btn.setMessage(Component.literal("Status: ")
                        .append(Component.literal(now ? "Enabled" : "Disabled")
                            .withColor(
                                now ? 0x55FF55 : 0xFF5555)));
                })
                .bounds(this.width / 2 - 75,
                    this.height / 2 - 20, 150, 20)
                .build());

            addRenderableWidget(Button.builder(
                Component.literal("Done"),
                btn -> {
                    if (this.minecraft != null) {
                        this.minecraft.setScreenAndShow(this.parent);
                    }
                })
                .bounds(this.width / 2 - 75,
                    this.height / 2 + 10, 150, 20)
                .build());
        }

        @Override
        public void extractRenderState(final GuiGraphicsExtractor context,
                          final int mouseX, final int mouseY,
                          final float delta) {
            super.extractRenderState(context, mouseX, mouseY, delta);
            context.centeredText(this.font, this.title,
                this.width / 2, 30, 0xFFFFFF);
        }
    }
}
