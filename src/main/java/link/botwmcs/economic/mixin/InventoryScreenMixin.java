package link.botwmcs.economic.mixin;

import link.botwmcs.economic.client.gui.PlayerBalanceComponent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InventoryScreen.class, priority = 1001)
public class InventoryScreenMixin {
    @Inject(method = "render", at = @At("TAIL"))
    protected void injectRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        PlayerBalanceComponent.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
