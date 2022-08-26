package de.chaoscaot.perfcounter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Perfcounter implements ModInitializer {
    public static long startTime = 0;

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> TimeCommand.register(dispatcher));
    }

    public static void sendMessage(String message) {
        MinecraftClient.getInstance().player.sendMessage(Text.literal(message));
    }
}
