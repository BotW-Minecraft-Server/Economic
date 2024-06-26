package link.botwmcs.economic.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ServerConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final ServerConfig CONFIG;

    public final ForgeConfigSpec.ConfigValue<Double> startingMoney;
    public final ForgeConfigSpec.ConfigValue<Double> maxMoney;
    public final ForgeConfigSpec.ConfigValue<Double> minMoney;
    public final ForgeConfigSpec.BooleanValue enableRound;
    public final ForgeConfigSpec.IntValue roundRate;
    public final ForgeConfigSpec.BooleanValue enableTax;
    public final ForgeConfigSpec.ConfigValue<String> taxType;
    public final ForgeConfigSpec.ConfigValue<Double> propertyMedian;

    static {
        Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    ServerConfig(ForgeConfigSpec.Builder builder) {
        builder.push("money");
        startingMoney = builder
                .comment("The amount of money that players start with.")
                .define("startingMoney", 0.00);
        maxMoney = builder
                .comment("The maximum amount of money that players can have.")
                .define("maxMoney", 1000000000.00);
        minMoney = builder
                .comment("The minimum amount of money that players can have.")
                .define("minMoney", 0.00);
        enableRound = builder
                .comment("Enable or disable rounding money.")
                .define("enableRound", true);
        roundRate = builder
                .comment("The number of decimal places to round money to.")
                .defineInRange("roundRate", 2, 1, 10);
        builder.pop();
        builder.push("tax");
        enableTax = builder
                .comment("Enable or disable tax.")
                .define("enableTax", true);
        List<String> s = List.of("ecohelper", "modern");
        taxType = builder
                .comment("The type of tax.")
                .define("taxType", "ecohelper", s::equals);
        propertyMedian = builder
                .comment("The average balance of single player in mathematical expectation.")
                .define("propertyMedian", 1000000.00);
        builder.pop();

    }
}
