package link.botwmcs.economic;

import com.mojang.logging.LogUtils;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import link.botwmcs.economic.command.EcoCommand;
import link.botwmcs.economic.config.ServerConfig;
import link.botwmcs.economic.event.player.PlayerEventHandler;
import link.botwmcs.economic.event.player.WorldJoinEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

public class Economic implements ModInitializer {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "economic";

    @Override
    public void onInitialize() {
        printConsoleOutput();
        registerCommands();
        registerEvents();
        registerConfigs();
    }

    private void printConsoleOutput() {
        LOGGER.info("Economic is loaded!");
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            EcoCommand.register(dispatcher);
        });
    }

    private void registerEvents() {
        PlayerEventHandler.PLAYER_LOGGED_IN_EVENT.register(WorldJoinEvent::onLoggedIn);
    }

    private void registerConfigs() {
        ForgeConfigRegistry.INSTANCE.register(Economic.MODID, ModConfig.Type.SERVER, ServerConfig.CONFIG_SPEC);
    }
}
