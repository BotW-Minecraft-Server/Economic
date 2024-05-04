package link.botwmcs.economic.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

import java.awt.*;

@Environment(EnvType.CLIENT)
public class BalanceTooltipRenderer {

    public static final ResourceLocation MONEY_ICON = new ResourceLocation("textures/gui/gold_coin.png");

    public static void register() {

    }



    public record WorthComponent(ItemStack stack, int width, int height, double worth) implements ClientTooltipComponent, TooltipComponent {
        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getWidth(Font font) {
            return width;
        }

        @Override
        public void renderImage(Font font, int tooltipX, int tooltipY, GuiGraphics guiGraphics) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MONEY_ICON);
            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(tooltipX - 1, tooltipY - 1, 100);
            guiGraphics.pose().scale(0.5f, 0.5f, 1f);
            RenderSystem.enableBlend();
            guiGraphics.blit(MONEY_ICON, 0, 0, 0, 0, 16, 16, 16, 16);
            guiGraphics.pose().popPose();
        }

        @Override
        public void renderText(Font font, int tooltipX, int tooltipY, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
            Minecraft mc = Minecraft.getInstance();
            Color textColor = Color.decode("#A8A8A8");
            String text = "x" + worth;
            mc.font.drawInBatch(text, tooltipX + 10, tooltipY - 1, textColor.getRGB(), true, matrix4f, bufferSource, Font.DisplayMode.NORMAL, 0, 15728880);
        }
    }
}
