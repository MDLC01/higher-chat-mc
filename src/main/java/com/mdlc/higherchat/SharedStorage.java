package com.mdlc.higherchat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;


/**
 * Contains data that is shared across multiple mixins.
 */
public final class SharedStorage {
    private SharedStorage() {
    }

    /**
     * The height of the no man's land (essentially, the max bar height) on the previous frame.
     * <p>
     * This is used to <a href="https://github.com/MDLC01/higher-chat-mc/issues/2">prevent the chat from wobbling with
     * the hunger bar</a>.
     */
    private static int lastNoMansLandHeight = 0;

    /**
     * Each frame, this variable is modified dynamically to contain the height of the highest bar that collides with the
     * chat.
     * <p>
     * Note that this value is measured from the top of the screen.
     */
    private static int maxBarHeight;

    /**
     * Returns the height of the window for the specified GUI.
     */
    private static int getHeight(Gui gui) {
        return gui.minecraft.getWindow().getGuiScaledHeight();
    }

    /**
     * Resets all data.
     * <p>
     * This function is called at the beginning of every frame.
     */
    public static void resetData(Gui gui) {
        maxBarHeight = getHeight(gui);
    }

    /**
     * Adapts the height of the chat knowing there is an icon at {@code (x, y)}.
     *
     * @param gui
     *         the current gui
     * @param x
     *         the abscissa of the leftmost pixel of the icon
     * @param y
     *         the ordinate of the high-most pixel of the icon
     */
    public static void declareIconAt(Gui gui, int x, int y) {
        if (x < gui.getChat().getWidth() && y < maxBarHeight) {
            maxBarHeight = y;
        }
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
        declareIconAt(Minecraft.getInstance().gui, x, y);
    }

    /**
     * Computes the optimal margin between the bottom of the screen and the chat.
     */
    public static int getOptimalChatMargin() {
        Gui gui = Minecraft.getInstance().gui;
        // Leave space for the `chat.queue` message
        boolean hasQueue = Minecraft.getInstance().getChatListener().queueSize() > 0;
        // If the bars used to be just a little lower, don't move the chat up.
        // This prevents https://github.com/MDLC01/higher-chat-mc/issues/2.
        int noMansLandHeight = maxBarHeight;
        if (lastNoMansLandHeight > maxBarHeight && lastNoMansLandHeight - maxBarHeight <= 2) {
            noMansLandHeight = lastNoMansLandHeight;
        }
        lastNoMansLandHeight = noMansLandHeight;
        int optimalBottomPos = noMansLandHeight - (hasQueue ? 10 : 1);
        if (optimalBottomPos < gui.getChat().getHeight()) {
            // If we cannot fit the chat between the top of the screen and the bars,
            // we move it back to its vanilla position.
            return 0;
        }
        return getHeight(gui) - optimalBottomPos;
    }
}
