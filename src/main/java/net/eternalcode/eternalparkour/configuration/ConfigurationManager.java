package net.eternalcode.eternalparkour.configuration;

import lombok.Getter;
import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.model.ChatConfigurationModel;
import net.eternalcode.eternalparkour.configuration.model.DatabaseConfigurationModel;
import net.eternalcode.eternalparkour.configuration.model.MessageConfigurationModel;
import panda.std.stream.PandaStream;

import java.io.File;

@Getter
public class ConfigurationManager {

    private final Cdn CDN = CdnFactory
            .createYamlLike()
            .getSettings()
            .build();

    private final EternalParkourPlugin eternalParkourPlugin = EternalParkourPlugin.getPluginInstance();

    private final File dataFolder = eternalParkourPlugin.getDataFolder();

    private final MessageConfigurationModel messageConfiguration = new MessageConfigurationModel(dataFolder, "messages.yml");
    private final DatabaseConfigurationModel databaseConfiguration = new DatabaseConfigurationModel(dataFolder, "database.yml");
    private final ChatConfigurationModel chatConfiguration = new ChatConfigurationModel(dataFolder, "chat.yml");


    public void init(){
        loadAndRender();
    }

    private void loadAndRender(){
        PandaStream.of(
                messageConfiguration,
                databaseConfiguration,
                chatConfiguration
                )
                .forEach(this::loadAndRender);
    }


    private <T extends ConfigWithResource> void loadAndRender(T config) {
        this.CDN.load(config.getResource(), config)
                .orThrow(RuntimeException::new);

        this.CDN.render(config, config.getResource())
                .orThrow(RuntimeException::new);
    }

    public <T extends ConfigWithResource> void render(T config) {
        this.CDN.render(config, config.getResource())
                .orThrow(RuntimeException::new);
    }

    public <T extends ConfigWithResource> void load(T config) {
        this.CDN.load(config.getResource(), config)
        	.orThrow(RuntimeException::new);
    }

}
