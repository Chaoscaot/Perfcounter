package de.chaoscaot.perfcounter.mixin;

import de.chaoscaot.perfcounter.Perfcounter;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.chaoscaot.perfcounter.Perfcounter.sendMessage;

@Mixin(DownloadingTerrainScreen.class)
public class DownloadTerrainScreenMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void constructorHead(CallbackInfo ci) {
        try {
            if(Perfcounter.startTime != 0) {
                long end = System.currentTimeMillis();
                long start = Perfcounter.startTime;
                long time = end - start;
                Perfcounter.startTime = 0;
                sendMessage(String.format("Loading took %.3fs", time/1000d));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
