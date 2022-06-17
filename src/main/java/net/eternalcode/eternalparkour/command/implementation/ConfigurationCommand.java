package net.eternalcode.eternalparkour.command.implementation;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.util.ChatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.bukkit.entity.Player;

@Section(route = "parkour")
public class ConfigurationCommand {

    private final StopWatch stopWatch = new StopWatch();
    private final ConfigurationManager configurationManager;

    private final static String BUILDING_CONFIG_MESSAGE = "&8> &7Started reloading configurations...";
    private final static String RELOAD_SUCCESS_MESSAGE = "&8> &2Finished reloading configurations in &7$(time-reload) &2ms...";

    public ConfigurationCommand(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }


    @Execute(route = "reload")
    public void reload(Player player){
        player.sendMessage(ChatUtils.component(BUILDING_CONFIG_MESSAGE));

        stopWatch.start();

        configurationManager.init();

        stopWatch.stop();

        String finalReloadSuccessMessage = RELOAD_SUCCESS_MESSAGE
                .replaceAll("\\$\\(time-reload\\)", String.valueOf(stopWatch.getTime()));

        player.sendMessage(ChatUtils.component(finalReloadSuccessMessage));
    }
}
