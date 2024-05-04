package link.botwmcs.economic.client.gui;

import link.botwmcs.economic.util.BalanceControl;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class PlayerBalanceComponent {
    public static void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        Minecraft mc = Minecraft.getInstance();
        String balance = "Balance: " + BalanceControl.getMoney(mc.player);
        guiGraphics.drawString(mc.font, balance, guiGraphics.guiWidth() / 2 - mc.font.width(balance) / 2, 10, 0xFFFFFF);
    }
}
