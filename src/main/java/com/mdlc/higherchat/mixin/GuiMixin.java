package com.mdlc.higherchat.mixin;

import com.mdlc.higherchat.SharedStorage;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;


@Mixin(Gui.class)
public abstract class GuiMixin {
    protected GuiMixin() {
    }

    /**
     * Runs before everything. Resets the values.
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        SharedStorage.resetData((Gui) (Object) this);
    }

    /**
     * Tests if a heart is higher than what we had seen until now.
     */
    @Redirect(method = "renderHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void onRenderHeart(GuiGraphics graphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation iconsLocation, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderType, iconsLocation, x, y, width, height);
    }

    /**
     * Tests if an armor piece is higher than what we had seen until now.
     */
    @Redirect(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private static void onRenderArmorPiece(GuiGraphics graphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation iconsLocation, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderType, iconsLocation, x, y, width, height);
    }

    /**
     * Tests if a food icon is higher than what we had seen until now.
     */
    @Redirect(method = "renderFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void onRenderFoodIcon(GuiGraphics graphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation iconsLocation, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderType, iconsLocation, x, y, width, height);
    }

    /**
     * Tests if a vehicle heart is higher than what we had seen until now.
     */
    @Redirect(method = "renderVehicleHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V"))
    private void onRenderVehicleHeart(GuiGraphics graphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation iconsLocation, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderType, iconsLocation, x, y, width, height);
    }
}
