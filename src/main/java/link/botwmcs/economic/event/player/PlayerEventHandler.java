package link.botwmcs.economic.event.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class PlayerEventHandler {
    private PlayerEventHandler() {
    }

    public static final Event<PlayerLoggedIn> PLAYER_LOGGED_IN_EVENT = EventFactory.createArrayBacked(PlayerLoggedIn.class, callbacks -> (world, serverPlayer, blockPos) -> {
        for (PlayerLoggedIn callback : callbacks) {
            callback.onPlayerLoggedIn(world, serverPlayer, blockPos);
        }
    });
    @FunctionalInterface
    public interface PlayerLoggedIn {
        void onPlayerLoggedIn(Level world, ServerPlayer serverPlayer, BlockPos blockPos);
    }
}
