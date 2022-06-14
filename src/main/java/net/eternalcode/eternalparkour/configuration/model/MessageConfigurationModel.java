package net.eternalcode.eternalparkour.configuration.model;

import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import net.eternalcode.eternalparkour.feature.message.MessageLanguage;
import net.eternalcode.eternalparkour.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Contextual
public class MessageConfigurationModel extends AbstractConfigWithResource {

    @Description("Language messages configuration model")
    public Map<MessageLanguage, Map<String, String>> messages =
            new HashMap<>(){{
                put(MessageLanguage.PL,
                        new HashMap<>(){{
                            put("HelloWorld", "Witaj Swiecie!");
                        }}
                );

                put(MessageLanguage.EN,
                        new HashMap<>(){{
                            put("HelloWorld", "Hello, World!");
                        }}
                );
            }};


    public MessageConfigurationModel(File folder, String child) {
        super(folder, child);

        EternalParkourPlugin eternalParkourPlugin = EternalParkourPlugin.getPluginInstance();

        eternalParkourPlugin.getSLF4JLogger().info("Initializing Message Configuration!");
    }


}
