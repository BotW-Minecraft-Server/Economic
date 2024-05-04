package link.botwmcs.economic.util;

import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.capability.entity.PlayerEcoData;
import net.minecraft.server.level.ServerPlayer;

public class PlayerUtil {

    public static void refreshPlayerData(ServerPlayer serverPlayer) {
        BalanceControl.addMoney(serverPlayer, 1);
        BalanceControl.subtractMoney(serverPlayer, 1);
    }
}
