package net.eternalcode.eternalparkour.configuration.model;

import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import net.eternalcode.eternalparkour.message.MessageLanguage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Contextual
public class MessageConfigurationModel extends AbstractConfigWithResource {

    private final static Logger LOGGER = LogManager.getLogger(MessageConfigurationModel.class);

    public MessageConfigurationModel(File folder, String child) {
        super(folder, child);


        LOGGER.info("Initializing message configuration...");
    }

    @Description("Language messages configuration model")
    public Map<MessageLanguage, Map<String, String>> messages =
            new HashMap<>(){{
                put(MessageLanguage.PL,
                        new HashMap<>(){{
                            put("joinMessage", "&7> &6Witaj ponownie na naszym serwerze.");
                            put("firstJoinMessage", "&7> &6Witaj po raz pierwszy na naszym serwerze.");
                            put("reloadConfigMessage", "&7> &6Przeładowano pliki konfiguracyjne pomyślnie.");
                            put("changeLanguageMessage", "&7> &6Zmieniono personalny język na &3polski&7.");
                            put("addExperienceToUserMessage", "&7> &6Pomyślnie dodano użytkownikowi &7$(user-name) &3$(xp-amount) &6doświadczenia.");
                        }}
                );

                put(MessageLanguage.EN,
                        new HashMap<>(){{
                            put("joinMessage", "&7> &6Welcome again on our server.");
                            put("firstJoinMessage", "&7> &6Welcome to the first time on our server.");
                            put("reloadConfigMessage", "&7> &6Config files reloaded successfully.");
                            put("changeLanguageMessage", "&7> &6Personal language has been changed to &3English&7.");
                            put("addExperienceToUserMessage", "&7> &3$(xp-amount) experience has been added successfully to user &7$(user-name)&6.");
                        }}
                );
            }};




}
