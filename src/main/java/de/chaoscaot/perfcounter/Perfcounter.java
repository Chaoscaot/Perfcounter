package de.chaoscaot.perfcounter;

import com.google.gson.Gson;
import de.chaoscaot.perfcounter.config.ConfigHolder;
import de.chaoscaot.perfcounter.config.TimerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Perfcounter implements ModInitializer {
    public static long startTime = 0;
    public static String command = "";

    private static final Gson gson = new Gson();

    @Override
    public void onInitialize() {
        AutoConfig.register(TimerConfig.class, GsonConfigSerializer::new);
        ConfigHolder.timerConfig = AutoConfig.getConfigHolder(TimerConfig.class).getConfig();
    }
}
