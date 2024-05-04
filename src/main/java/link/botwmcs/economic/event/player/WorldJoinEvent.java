package link.botwmcs.economic.event.player;

import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.util.BalanceControl;
import link.botwmcs.economic.util.PlayerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class WorldJoinEvent {
    public static void onLoggedIn(Level world, ServerPlayer serverPlayer, BlockPos blockPos) {
        if (CapabilityRegister.PLAYER_DATA.get(serverPlayer).getIsFirstJoin()) {
            serverPlayer.sendSystemMessage(net.minecraft.network.chat.Component.nullToEmpty("Welcome to LTSX!"));
            BalanceControl.resetMoney(serverPlayer);

            // All things down. Set to false after all things done.
            CapabilityRegister.PLAYER_DATA.get(serverPlayer).setIsFirstJoin(false);
        } else {
            serverPlayer.sendSystemMessage(net.minecraft.network.chat.Component.nullToEmpty("Welcome back to LTSX!"));
        }

        PlayerUtil.refreshPlayerData(serverPlayer);
    }
}
