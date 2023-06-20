package com.mdlc.higherchat.mixin;

import com.mdlc.higherchat.SharedStorage;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow private int screenHeight;
    @Shadow @Final private ChatComponent chat;

    protected GuiMixin() {
    }

    /**
     * Runs before everything. Resets the values.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics graphics, float partialTick, CallbackInfo ci) {
        SharedStorage.resetData(this.chat.getWidth(), this.screenHeight);
    }

    /**
     * Tests if an icon is higher than what we had seen until now.
     */
    @Redirect(method = "renderPlayerHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void onRenderBarIcon(GuiGraphics graphics, ResourceLocation iconsLocation, int x, int y, int xOffset, int yOffset, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blit(iconsLocation, x, y, xOffset, yOffset, width, height);
    }

    /**
     * Tests if a heart is higher than what we had seen until now.
     */
    @Redirect(method = "renderHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    private void onRenderHeart(GuiGraphics graphics, ResourceLocation iconsLocation, int x, int y, int xOffset, int yOffset, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blit(iconsLocation, x, y, xOffset, yOffset, width, height);
    }
}
