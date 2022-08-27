package de.chaoscaot.perfcounter.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "perfcounter")
public class TimerConfig implements ConfigData {

    public boolean enabled = true;

    public boolean showInChat = true;
}
