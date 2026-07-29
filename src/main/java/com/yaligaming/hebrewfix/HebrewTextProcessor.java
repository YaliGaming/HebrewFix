package com.yaligaming.hebrewfix;

public final class HebrewTextProcessor {

    private static final int HEBREW_START = 0x0590;
    private static final int HEBREW_END = 0x05FF;
    private static final int HEBREW_SUPP_START = 0xFB1D;
    private static final int HEBREW_SUPP_END = 0xFB4F;

    private HebrewTextProcessor() {}

    public static boolean isHebrew(final int codePoint) {
        return (codePoint >= HEBREW_START && codePoint <= HEBREW_END)
            || (codePoint >= HEBREW_SUPP_START && codePoint <= HEBREW_SUPP_END);
    }

    public static boolean containsHebrew(final String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            final char ch = text.charAt(i);
            if ((ch >= HEBREW_START && ch <= HEBREW_END)
                    || (ch >= HEBREW_SUPP_START && ch <= HEBREW_SUPP_END)) {
                return true;
            }
        }
        return false;
    }
}
