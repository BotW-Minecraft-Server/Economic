package link.botwmcs.economic.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import link.botwmcs.economic.capability.entity.CapabilityRegister;
import link.botwmcs.economic.config.ServerConfig;
import link.botwmcs.economic.event.player.PlayerEventHandler;
import link.botwmcs.economic.util.BalanceControl;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class EcoCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("eco")
                .then(Commands.literal("balance")
                        .requires(CommandSourceStack::isPlayer)
                        .executes(context -> {
                            double balance = BalanceControl.getMoney(context.getSource().getPlayer());
                            context.getSource().sendSystemMessage(Component.nullToEmpty("Your balance: " + balance));
                            return 1;
                        })
                )
                .then(Commands.literal("pay")
                        .requires(CommandSourceStack::isPlayer)
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(1))
                                        .executes(context -> {
                                            if (BalanceControl.pay(context.getSource().getPlayer(), EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"))) {
                                                context.getSource().sendSystemMessage(Component.nullToEmpty("Payment successful!"));
                                                return 1;
                                            } else {
                                                context.getSource().sendSystemMessage(Component.nullToEmpty("You don't have enough money!"));
                                                return 0;
                                            }
                                        })
                                )
                        )
                )
                .then(Commands.literal("manage")
                        .requires(source -> source.hasPermission(4))
                        .then(Commands.literal("set")
                                .then(Commands.argument("target", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(ServerConfig.CONFIG.minMoney.get(), ServerConfig.CONFIG.maxMoney.get()))
                                                .executes(context -> {
                                                            BalanceControl.setMoney(EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"));
                                                            return 1;
                                                        }
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("add")
                                .then(Commands.argument("target", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0, ServerConfig.CONFIG.maxMoney.get()))
                                                .executes(context -> {
                                                            BalanceControl.addMoney(EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"));
                                                            return 1;
                                                        }
                                                )
                                        )
                                )
                        )
                        .then(Commands.literal("remove")
                                .then(Commands.argument("target", EntityArgument.players())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0, ServerConfig.CONFIG.minMoney.get()))
                                                .executes(context -> {
                                                            BalanceControl.subtractMoney(EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"));
                                                            return 1;
                                                        }
                                                )
                                        )
                                )
                        )
                )
        );
    }

}
