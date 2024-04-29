package link.botwmcs.economic.capability.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;

public class PlayerEcoData implements IPlayerEcoData, AutoSyncedComponent {
    private final Object provider;
    private Integer playerMoney;
    private Boolean playerIsFirstJoin = true;
    public PlayerEcoData(Object provider) {
        this.provider = provider;
    }

    @Override
    public Integer getMoney() {
        if (playerMoney == null) {
            return 0;
        }
        return playerMoney;
    }

    @Override
    public Boolean getIsFirstJoin() {
        return playerIsFirstJoin;
    }

    @Override
    public void setMoney(Integer money) {
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
        playerMoney = tag.getInt("money");
        playerIsFirstJoin = tag.getBoolean("isFirstJoin");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if (playerMoney == null) {
            playerMoney = 0;
        }
        tag.putInt("money", playerMoney);
        tag.putBoolean("isFirstJoin", playerIsFirstJoin);
    }

}
