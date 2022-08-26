package de.chaoscaot.perfcounter;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;


public class TimeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("timer")
                        .then(argument("command", greedyString())
                                .executes(ctx -> {
                                    String command = getString(ctx, "command");
                                    System.out.println(command);
                                    MinecraftClient.getInstance().player.sendCommand(command.startsWith("/") ? command.substring(1) : command);
                                    Perfcounter.startTime = System.currentTimeMillis();
                                    ctx.getSource().sendMessage(Text.literal("Timer started"));
                                    return Command.SINGLE_SUCCESS;
                                })));
    }
}
