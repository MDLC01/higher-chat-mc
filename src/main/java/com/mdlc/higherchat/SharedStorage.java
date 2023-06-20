package com.mdlc.higherchat;

/**
 * Contains data that is shared across multiple mixins.
 */
public final class SharedStorage {
    private SharedStorage() {
    }

    private static int screenHeight;

    /**
     * Updated each frame to contain the chat width.
     */
    private static int chatWidth;

    /**
     * Each frame, this variable is modified dynamically to contain the height of the highest bar that collides with the
     * chat.
     */
    private static int maxBarHeight;

    /**
     * Resets all data.
     * <p>
     * This function is called at the beginning of every frame.
     */
    public static void resetData(int chatWidth, int screenHeight) {
        SharedStorage.screenHeight = screenHeight;
        SharedStorage.chatWidth = chatWidth;
        maxBarHeight = screenHeight;
    }

    /**
     * Adapts the height of the chat knowing there is an icon at {@code (x, y)}.
     *
     * @param x
     *         the abscissa of the leftmost pixel of the icon
     * @param y
     *         the ordinate of the high-most pixel of the icon
     */
    public static void declareIconAt(int x, int y) {
        if (x < chatWidth && y < maxBarHeight) {
            maxBarHeight = y;
        }
    }

    /**
     * Computes the optimal margin between the bottom of the screen and the chat.
     */
    public static int getOptimalChatMargin() {
        return screenHeight - maxBarHeight + 1;
    }
}
