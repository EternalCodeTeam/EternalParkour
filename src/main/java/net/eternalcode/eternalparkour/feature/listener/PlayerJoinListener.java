package net.eternalcode.eternalparkour.feature.listener;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.configuration.model.MessageConfigurationModel;
import net.eternalcode.eternalparkour.feature.message.MessageLanguage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import panda.std.stream.PandaStream;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class PlayerJoinListener implements Listener{

    private final EternalParkourPlugin plugin = EternalParkourPlugin.getPluginInstance();
    private final ConfigurationManager configurationManager =
            plugin.getConfigurationManager();



    @EventHandler
    public void onJoin(PlayerJoinEvent event){
    }
}
