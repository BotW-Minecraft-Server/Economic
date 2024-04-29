package link.botwmcs.economic.capability.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import link.botwmcs.economic.Economic;
import net.minecraft.resources.ResourceLocation;

public class CapabilityRegister implements EntityComponentInitializer {

    public static final ComponentKey<IPlayerEcoData> PLAYER_DATA = ComponentRegistry.getOrCreate(new ResourceLocation(Economic.MODID, "player_eco_data"), IPlayerEcoData.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYER_DATA, PlayerEcoData::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
