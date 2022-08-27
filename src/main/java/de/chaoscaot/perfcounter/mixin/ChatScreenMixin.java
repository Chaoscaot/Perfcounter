package de.chaoscaot.perfcounter.mixin;

import de.chaoscaot.perfcounter.Perfcounter;
import de.chaoscaot.perfcounter.config.ConfigHolder;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

    @Inject(method = "sendMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendCommand(Ljava/lang/String;Lnet/minecraft/text/Text;)V"))
    public void sendCommandOverride(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
        if(ConfigHolder.timerConfig.enabled) {
            Perfcounter.startTime = System.currentTimeMillis();
            Perfcounter.command = chatText;
        }
    }
}
