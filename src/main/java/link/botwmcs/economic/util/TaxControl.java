package link.botwmcs.economic.util;

import link.botwmcs.economic.config.ServerConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class TaxControl {

    private static double ecohelperMode(double price, double property) {
        // 0.5 is squareroot
        double basicProperty = ServerConfig.CONFIG.propertyMedian.get();
        return ((price) / Math.pow(Math.exp((property / basicProperty)), 0.5) + (price / (1 + property / basicProperty))) / 2;
    }

    public static double calculateTax(double currentPrice, ServerPlayer serverPlayer) {
        if (ServerConfig.CONFIG.taxType.get().equals("ecohelper")) {
            double playerBalance = BalanceControl.getMoney(serverPlayer);
            double finalPrice = ecohelperMode(currentPrice, playerBalance);
            return BalanceControl.roundMoney(finalPrice);
        } else {
            // todo: modern module
            return currentPrice;
        }
    }

//    public static double getItemWorth(double price, ItemStack itemStack) {
//
//    }
}
