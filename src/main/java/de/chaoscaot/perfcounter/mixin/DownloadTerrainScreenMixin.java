package de.chaoscaot.perfcounter.mixin;

import com.google.gson.Gson;
import de.chaoscaot.perfcounter.Perfcounter;
import de.chaoscaot.perfcounter.config.ConfigHolder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadTerrainScreenMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void constructorHead(CallbackInfo ci) {
        try {
            if(Perfcounter.startTime != 0) {
                long end = System.currentTimeMillis();
                long start = Perfcounter.startTime;
                long time = end - start;

                if(ConfigHolder.timerConfig.showInChat) {
                    MinecraftClient.getInstance().player.sendMessage(Text.literal(String.format("From command (%s) to world load took %.3fs", Perfcounter.command, time / 1000.0)));
                } else {
                    MinecraftClient.getInstance().getToastManager().add(new SystemToast(SystemToast.Type.PERIODIC_NOTIFICATION, Text.of("Loading Time"), Text.of(String.format("From command to world load took %.3fs", time / 1000.0))));
                }
                Perfcounter.startTime = 0;
                Perfcounter.command = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
