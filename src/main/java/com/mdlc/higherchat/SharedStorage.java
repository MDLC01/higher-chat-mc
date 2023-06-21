package com.mdlc.higherchat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;


/**
 * Contains data that is shared across multiple mixins.
 */
public final class SharedStorage {
    private SharedStorage() {
    }

    /**
     * Updated each frame to contain the height of the screen.
     */
    private static int screenHeight;

    /**
     * Updated each frame to contain the chat width.
     */
    private static int chatWidth;

    /**
     * Updated each frame to contain the chat height.
     */
    private static int chatHeight;

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
    public static void resetData(ChatComponent chat, int screenHeight) {
        SharedStorage.screenHeight = screenHeight;
        chatWidth = chat.getWidth();
        chatHeight = chat.getHeight();
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
        // Leave space for the `chat.queue` message
        boolean hasQueue = Minecraft.getInstance().getChatListener().queueSize() > 0;
        int optimalBottomPos = maxBarHeight - (hasQueue ? 10 : 1);
        if (optimalBottomPos < chatHeight) {
            // If we cannot fit the chat between the top of the screen and the bars,
            // we move it back to its vanilla position.
            return 0;
        }
        return screenHeight - optimalBottomPos;
    }
}
