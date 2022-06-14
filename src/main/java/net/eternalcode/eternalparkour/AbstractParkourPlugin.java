package net.eternalcode.eternalparkour;

import org.bukkit.plugin.java.JavaPlugin;

public class AbstractParkourPlugin extends JavaPlugin {

    private static AbstractParkourPlugin pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;
    }


    public static AbstractParkourPlugin getPluginInstance() {
        return pluginInstance;
    }
}
