package link.botwmcs.economic.capability.entity;

import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

public interface IPlayerEcoData extends PlayerComponent {
    Double getMoney();
    Boolean getIsFirstJoin();
    void setMoney(Double money);
    void setIsFirstJoin(Boolean isFirstJoin);
}
