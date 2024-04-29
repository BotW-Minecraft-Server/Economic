package link.botwmcs.economic.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ServerConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final ServerConfig CONFIG;

    public final ForgeConfigSpec.IntValue startingMoney;
    public final ForgeConfigSpec.IntValue maxMoney;
    public final ForgeConfigSpec.IntValue minMoney;

    static {
        Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    ServerConfig(ForgeConfigSpec.Builder builder) {
        builder.push("money");
        startingMoney = builder
                .comment("The amount of money that players start with.")
                .defineInRange("startingMoney", 0, 0, Integer.MAX_VALUE);
        maxMoney = builder
                .comment("The maximum amount of money that players can have.")
                .defineInRange("maxMoney", 100000000, 0, Integer.MAX_VALUE);
        minMoney = builder
                .comment("The minimum amount of money that players can have.")
                .defineInRange("minMoney", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        builder.pop();
    }
}
