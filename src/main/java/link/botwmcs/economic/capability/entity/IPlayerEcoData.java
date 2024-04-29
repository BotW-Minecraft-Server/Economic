package link.botwmcs.economic.capability.entity;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface IPlayerEcoData extends PlayerComponent {
    Integer getMoney();
    Boolean getIsFirstJoin();
    void setMoney(Integer money);
    void setIsFirstJoin(Boolean isFirstJoin);
}
