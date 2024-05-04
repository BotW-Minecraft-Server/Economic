package link.botwmcs.economic.capability.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ClientTickingComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerEcoData implements IPlayerEcoData, AutoSyncedComponent {
    private final Object provider;
    private Double playerMoney;
    private Boolean playerIsFirstJoin = true;
    public PlayerEcoData(Object provider) {
        this.provider = provider;
    }

    @Override
    public Double getMoney() {
        if (playerMoney == null) {
            return 0.00;
        }
        return playerMoney;
    }

    @Override
    public Boolean getIsFirstJoin() {
        return playerIsFirstJoin;
    }

    @Override
    public void setMoney(Double money) {
        this.playerMoney = money;
        CapabilityRegister.PLAYER_DATA.sync(this.provider);
    }

    @Override
    public void setIsFirstJoin(Boolean isFirstJoin) {
        this.playerIsFirstJoin = isFirstJoin;
        CapabilityRegister.PLAYER_DATA.sync(this.provider);
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        playerMoney = tag.getDouble("money");
        playerIsFirstJoin = tag.getBoolean("isFirstJoin");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if (playerMoney == null) {
            playerMoney = 0.00;
        }
        tag.putDouble("money", playerMoney);
        tag.putBoolean("isFirstJoin", playerIsFirstJoin);
    }

}
