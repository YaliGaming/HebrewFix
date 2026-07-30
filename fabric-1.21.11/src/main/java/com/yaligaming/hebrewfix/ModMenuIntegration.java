package com.yaligaming.hebrewfix;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

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
            super(Text.literal("HebrewFix"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            final boolean enabled = HebrewFixConfig.isEnabled();

            addDrawableChild(ButtonWidget.builder(
                Text.literal("Status: ")
                    .append(Text.literal(enabled ? "Enabled" : "Disabled")
                        .styled(s -> s.withColor(
                            enabled ? 0x55FF55 : 0xFF5555))),
                btn -> {
                    HebrewFixConfig.toggle();
                    final boolean now = HebrewFixConfig.isEnabled();
                    btn.setMessage(Text.literal("Status: ")
                        .append(Text.literal(now ? "Enabled" : "Disabled")
                            .styled(s -> s.withColor(
                                now ? 0x55FF55 : 0xFF5555))));
                })
                .dimensions(this.width / 2 - 75,
                    this.height / 2 - 20, 150, 20)
                .build());

            addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                btn -> {
                    if (this.client != null) {
                        this.client.setScreen(this.parent);
                    }
                })
                .dimensions(this.width / 2 - 75,
                    this.height / 2 + 10, 150, 20)
                .build());
        }

        @Override
        public void render(final DrawContext context,
                          final int mouseX, final int mouseY,
                          final float delta) {
            super.render(context, mouseX, mouseY, delta);
            context.drawCenteredTextWithShadow(
                this.textRenderer, this.title,
                this.width / 2, 30, 0xFFFFFF);
        }
    }
}
