package link.botwmcs.economic.util;

import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.config.ServerConfig;
import net.minecraft.server.level.ServerPlayer;

import java.lang.module.Configuration;

public class BalanceControl {
    // Getter Setter Adder Subtractor
    public static double getMoney(ServerPlayer serverPlayer) {
        return roundMoney(CapabilityRegister.PLAYER_DATA.get(serverPlayer).getMoney());
    }

    public static void setMoney(ServerPlayer serverPlayer, double money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(safetyCheck(roundMoney(money)));
    }

    public static void addMoney(ServerPlayer serverPlayer, double money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(getMoney(serverPlayer) + money);
    }

    public static void subtractMoney(ServerPlayer serverPlayer, double money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(getMoney(serverPlayer) - money);
    }

    public static double safetyCheck(double money) {
        if (money < ServerConfig.CONFIG.minMoney.get()) {
            return ServerConfig.CONFIG.minMoney.get();
        } else if (money > ServerConfig.CONFIG.maxMoney.get()) {
            return ServerConfig.CONFIG.maxMoney.get();
        } else {
            return money;
        }
    }

    public static double roundMoney(double money) {
        if (ServerConfig.CONFIG.enableRound.get()) {
            return Math.round(money * 100.0) / 100.0;
        } else {
            return money;
        }
    }

    public static void resetMoney(ServerPlayer serverPlayer) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(ServerConfig.CONFIG.startingMoney.get());
    }

    public static boolean hasEnoughMoney(ServerPlayer serverPlayer, double money) {
        return getMoney(serverPlayer) >= money;
    }

    public static boolean pay(ServerPlayer payer, ServerPlayer payee, double amount) {
        if (hasEnoughMoney(payer, amount)) {
            subtractMoney(payer, amount);
            addMoney(payee, amount);
            return true;
        } else {
            return false;
        }
    }

}
