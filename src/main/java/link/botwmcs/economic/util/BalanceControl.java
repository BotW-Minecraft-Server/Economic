package link.botwmcs.economic.util;

import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.config.ServerConfig;
import net.minecraft.server.level.ServerPlayer;

public class BalanceControl {
    // Getter Setter Adder Subtractor
    public static int getMoney(ServerPlayer serverPlayer) {
        return CapabilityRegister.PLAYER_DATA.get(serverPlayer).getMoney();
    }

    public static void setMoney(ServerPlayer serverPlayer, int money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(safetyCheck(money));
    }

    public static void addMoney(ServerPlayer serverPlayer, int money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(getMoney(serverPlayer) + money);
    }

    public static void subtractMoney(ServerPlayer serverPlayer, int money) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(getMoney(serverPlayer) - money);
    }

    public static int safetyCheck(int money) {
        if (money < ServerConfig.CONFIG.minMoney.get()) {
            return ServerConfig.CONFIG.minMoney.get();
        } else if (money > ServerConfig.CONFIG.maxMoney.get()) {
            return ServerConfig.CONFIG.maxMoney.get();
        } else {
            return money;
        }
    }

    public static void resetMoney(ServerPlayer serverPlayer) {
        CapabilityRegister.PLAYER_DATA.get(serverPlayer).setMoney(ServerConfig.CONFIG.startingMoney.get());
    }

    public static void removeMoney(ServerPlayer target, int amount) {
        CapabilityRegister.PLAYER_DATA.get(target).setMoney(CapabilityRegister.PLAYER_DATA.get(target).getMoney() - amount);
    }

    public static boolean hasEnoughMoney(ServerPlayer serverPlayer, int money) {
        return getMoney(serverPlayer) >= money;
    }

    public static boolean pay(ServerPlayer payer, ServerPlayer payee, int amount) {
        if (hasEnoughMoney(payer, amount)) {
            removeMoney(payer, amount);
            addMoney(payee, amount);
            return true;
        } else {
            return false;
        }
    }

}
