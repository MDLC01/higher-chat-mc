package com.mdlc.higherchat.mixin;

import com.mdlc.higherchat.SharedStorage;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(ChatComponent.class)
public abstract class ChatComponentMixin {
    /**
     * Makes the chat render above bars.
     */
    @ModifyConstant(method = "render", constant = @Constant(intValue = 40))
    private int adjustBottomMarginInRender(int bottomMargin) {
        return Math.max(bottomMargin, SharedStorage.getOptimalChatMargin());
    }

    /**
     * Moves the bounding box of the {@code chat.queue} button with its visual position.
     */
    @ModifyConstant(method = "handleChatQueueClicked", constant = @Constant(doubleValue = 40.0))
    private double adjustBottomMarginInHandleChatQueueClicked(double bottomMargin) {
        return Math.max(bottomMargin, SharedStorage.getOptimalChatMargin());
    }

    /**
     * Modifies {@link ChatComponent#screenToChatY(double)} appropriately.
     */
    @ModifyConstant(method = "screenToChatY", constant = @Constant(doubleValue = 40.0))
    private double adjustBottomMarginInScreenToChatY(double bottomMargin) {
        return Math.max(bottomMargin, SharedStorage.getOptimalChatMargin());
    }
}
