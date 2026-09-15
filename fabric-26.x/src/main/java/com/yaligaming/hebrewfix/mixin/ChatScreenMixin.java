package com.yaligaming.hebrewfix.mixin;

import com.yaligaming.hebrewfix.HebrewFixConfig;
import com.yaligaming.hebrewfix.HebrewTextProcessor;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Style;
import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Shadow
    protected boolean isDraft;

    private static boolean isSpace(int cp) {
        return cp == ' ';
    }

    private static boolean isNeutral(int cp) {
        return cp == ',' || cp == '.' || cp == '!' || cp == '?'
            || cp == ':' || cp == ';' || cp == '-'
            || cp == '\'' || cp == '"' || cp == '(' || cp == ')'
            || cp == '[' || cp == ']';
    }

    private static boolean isRtl(int cp) {
        return HebrewTextProcessor.isHebrew(cp) || isNeutral(cp);
    }

    @Inject(
        method = "formatChat(Ljava/lang/String;I)"
              + "Lnet/minecraft/util/FormattedCharSequence;",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hebrewfix$fixChatFormat(
            String text,
            int firstCharacterIndex,
            CallbackInfoReturnable<FormattedCharSequence> cir) {
        if (text == null || text.isEmpty()
                || text.startsWith("/")
                || !HebrewTextProcessor.containsHebrew(text)
                || !HebrewFixConfig.isEnabled()) {
            return;
        }

        Style style = this.isDraft
            ? Style.EMPTY.withColor(ChatFormatting.GRAY).withItalic(true)
            : Style.EMPTY;

        boolean hasEnglish = false;
        for (int t = 0; t < text.length();) {
            int cp = text.codePointAt(t);
            if (!isRtl(cp) && !isSpace(cp)) {
                hasEnglish = true;
                break;
            }
            t += Character.charCount(cp);
        }

        if (hasEnglish) {
            int[] ord = buildMixedVisitOrder(text);
            cir.setReturnValue(visitor -> {
                for (int vi = 0; vi < ord.length; vi++) {
                    if (!visitor.accept(vi, style,
                            text.codePointAt(ord[vi]))) {
                        return false;
                    }
                }
                return true;
            });
        } else {
            cir.setReturnValue(
                FormattedCharSequence.backward(text, style));
        }
    }

    private static int[] buildMixedVisitOrder(String text) {
        List<Integer> order = new ArrayList<>(text.length());
        int i = 0;
        while (i < text.length()) {
            int cp = text.codePointAt(i);
            if (isRtl(cp)) {
                List<Integer> run = new ArrayList<>();
                while (i < text.length()) {
                    int c2 = text.codePointAt(i);
                    if (isSpace(c2)) {
                        if (spaceLeadsToRtl(text, i)) {
                            run.add(i);
                            i++;
                        } else {
                            break;
                        }
                    } else if (isRtl(c2)) {
                        run.add(i);
                        i += Character.charCount(c2);
                    } else {
                        break;
                    }
                }
                for (int j = run.size() - 1; j >= 0; j--) {
                    order.add(run.get(j));
                }
            } else {
                order.add(i);
                i += Character.charCount(cp);
            }
        }
        return order.stream().mapToInt(x -> x).toArray();
    }

    private static boolean spaceLeadsToRtl(String text, int pos) {
        int look = pos + 1;
        while (look < text.length()) {
            int lc = text.codePointAt(look);
            if (isRtl(lc)) {
                return true;
            } else if (!isSpace(lc)) {
                return false;
            }
            look += Character.charCount(lc);
        }
        return false;
    }
}
