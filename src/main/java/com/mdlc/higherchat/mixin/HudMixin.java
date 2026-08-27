package com.mdlc.higherchat.mixin;

import com.mdlc.higherchat.SharedStorage;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.resources.Identifier;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Hud.class)
public abstract class HudMixin {
    @Shadow @Final private Minecraft minecraft;

    protected HudMixin() {
    }

    /**
     * Runs before everything. Resets the values.
     */
    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void onRender(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        SharedStorage.resetData(minecraft);
    }

    /**
     * Tests if a heart is higher than what we had seen until now.
     */
    @Redirect(method = "extractHeart", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onRenderHeart(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier icons, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderPipeline, icons, x, y, width, height);
    }

    /**
     * Tests if an armor piece is higher than what we had seen until now.
     */
    @Redirect(method = "extractArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void onRenderArmorPiece(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier icons, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderPipeline, icons, x, y, width, height);
    }

    /**
     * Tests if a food icon is higher than what we had seen until now.
     */
    @Redirect(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onRenderFoodIcon(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier icons, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderPipeline, icons, x, y, width, height);
    }

    /**
     * Tests if a vehicle heart is higher than what we had seen until now.
     */
    @Redirect(method = "extractVehicleHealth", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void onRenderVehicleHeart(GuiGraphicsExtractor graphics, RenderPipeline renderPipeline, Identifier icons, int x, int y, int width, int height) {
        SharedStorage.declareIconAt(x, y);
        graphics.blitSprite(renderPipeline, icons, x, y, width, height);
    }
}
