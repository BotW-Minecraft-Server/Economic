package link.botwmcs.economic.util;

import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.config.ServerConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.lang.module.Configuration;

public class BalanceControl {
    // Getter Setter Adder Subtractor
    public static double getMoney(Player player) {
        return roundMoney(CapabilityRegister.PLAYER_DATA.get(player).getMoney());
    }

    public static void setMoney(Player player, double money) {
        CapabilityRegister.PLAYER_DATA.get(player).setMoney(safetyCheck(roundMoney(money)));
    }

    public static void addMoney(Player player, double money) {
        CapabilityRegister.PLAYER_DATA.get(player).setMoney(getMoney(player) + money);
    }

    public static void subtractMoney(Player player, double money) {
        CapabilityRegister.PLAYER_DATA.get(player).setMoney(getMoney(player) - money);
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
            return Math.round(money * Math.pow(10, ServerConfig.CONFIG.roundRate.get())) / Math.pow(10, ServerConfig.CONFIG.roundRate.get());
        } else {
            return money;
        }
    }

    public static void resetMoney(Player player) {
        CapabilityRegister.PLAYER_DATA.get(player).setMoney(ServerConfig.CONFIG.startingMoney.get());
    }

    public static boolean hasEnoughMoney(Player player, double money) {
        return getMoney(player) >= money;
    }

    public static boolean pay(Player payer, Player payee, double amount) {
        if (hasEnoughMoney(payer, amount)) {
            subtractMoney(payer, amount);
            addMoney(payee, amount);
            return true;
        } else {
            return false;
        }
    }

}
