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
    @ModifyConstant(method = "render(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IIZ)V", constant = @Constant(intValue = 40))
    private int adjustBottomMarginInRender(int bottomMargin) {
        return Math.max(bottomMargin, SharedStorage.getOptimalChatMargin());
    }
}
