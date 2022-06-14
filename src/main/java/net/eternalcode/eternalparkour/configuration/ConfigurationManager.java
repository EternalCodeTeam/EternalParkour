package net.eternalcode.eternalparkour.configuration;

import lombok.Getter;
import net.dzikoysk.cdn.Cdn;
import net.dzikoysk.cdn.CdnFactory;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.model.DatabaseConfigurationModel;
import net.eternalcode.eternalparkour.configuration.model.MessageConfigurationModel;
import net.eternalcode.eternalparkour.feature.message.MessageLanguage;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Panda;
import panda.std.Option;
import panda.std.stream.PandaStream;

import java.io.File;
import java.util.Map;

@Getter
public class ConfigurationManager {

    private final Cdn CDN = CdnFactory
            .createYamlLike()
            .getSettings()
            .build();

    private final EternalParkourPlugin eternalParkourPlugin = EternalParkourPlugin.getPluginInstance();

    private final File dataFolder = eternalParkourPlugin.getDataFolder();

    private MessageConfigurationModel messageConfiguration;
    private DatabaseConfigurationModel databaseConfiguration;


    public void init(){
        this.messageConfiguration = new MessageConfigurationModel(dataFolder, "messages.yml");
        this.databaseConfiguration = new DatabaseConfigurationModel(dataFolder, "database.yml");

        loadAndRender();
    }

    private void loadAndRender(){
        PandaStream.of(
                messageConfiguration,
                databaseConfiguration
                )
                .forEach(this::loadAndRender);
    }


    private <T extends ConfigWithResource> void loadAndRender(T config) {
        this.CDN.load(config.getResource(), config)
                .orThrow(RuntimeException::new);

        this.CDN.render(config, config.getResource())
                .orThrow(RuntimeException::new);
    }

    private <T extends ConfigWithResource> void render(T config) {
        this.CDN.render(config, config.getResource())
                .orThrow(RuntimeException::new);
    }

    public Map<String, String> getMessagesByLanguage(MessageLanguage language){
        return PandaStream.of(
                messageConfiguration.messages.entrySet()
        )
                .find(langEntrySet -> langEntrySet.getKey() == language)
                .map(Map.Entry::getValue)
                .get();
    }

    public Option<String> getByKey(String key, MessageLanguage language){
        return PandaStream.of(getMessagesByLanguage(language).entrySet())
                .find(o -> o.getKey().equalsIgnoreCase(key))
                .map(Map.Entry::getValue);
    }
}
