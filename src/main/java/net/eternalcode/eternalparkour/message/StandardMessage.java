package net.eternalcode.eternalparkour.message;


import lombok.Getter;
import lombok.Setter;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.configuration.model.MessageConfigurationModel;
import net.eternalcode.eternalparkour.util.ChatUtils;
import org.apache.commons.lang.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import panda.std.Option;
import panda.std.stream.PandaStream;

import java.util.Map;

@Getter
@Setter
public class StandardMessage {

    private MessageLanguage messageLanguage;
    private MessageSender messageType;
    private String context;
    private String key;

    private final Logger logger = LogManager.getLogger(StandardMessage.class);

    private final ConfigurationManager configurationManager
            = EternalParkourPlugin.getPluginInstance().getConfigurationManager();

    private final MessageConfigurationModel messageConfigurationModel
            = configurationManager.getMessageConfiguration();


    public StandardMessage(MessageLanguage messageLanguage, MessageSender messageType){
        this.messageLanguage = messageLanguage;
        this.messageType = messageType;

    }

    public StandardMessage(String key, MessageLanguage messageLanguage, MessageSender messageType, String context) {
        this.key = key;
        this.messageLanguage = messageLanguage;
        this.messageType = messageType;
        this.context = context;
    }

    public StandardMessage(String key){
        this.key = key;
    }

    public StandardMessage withLanguage(MessageLanguage messageLanguage){
        this.messageLanguage = messageLanguage;

        return this;
    }

    public StandardMessage withContext(String context){
        this.context = context;

        return this;
    }

    public StandardMessage withType(MessageSender messageType){
        this.messageType = messageType;

        return this;
    }


    public Map<String, String> getMessagesByLanguage(MessageLanguage language){
        return PandaStream.of(
                        messageConfigurationModel.messages.entrySet()
                )
                .find(langEntrySet -> langEntrySet.getKey() == language)
                .map(Map.Entry::getValue)
                .get();
    }

    public Option<String> getMessageKeyByContext(String context, MessageLanguage language){
        Map<String, String> languageMessages = getMessagesByLanguage(language);

        return PandaStream.of(languageMessages.entrySet())
                .find(entry -> entry.getValue().equalsIgnoreCase(context))
                .map(Map.Entry::getKey);
    }

    public void saveMessages(boolean printLogs){
        if(printLogs)
            logger.info("Saving messages...");

        configurationManager.render(messageConfigurationModel);
    }

    public void printMessage(Player player){


        switch(messageType){
            case PLAYER -> {
                Validate.notNull(player);


                player.sendMessage(ChatUtils.component(getByKey(this).get()));
            }

            case CONSOLE -> {
                ConsoleCommandSender consoleCommandSender
                        = Bukkit.getConsoleSender();

                consoleCommandSender.sendMessage(ChatUtils.component(getByKey(this).get()));
            }
        }
    }

    public boolean modifyMessage(StandardMessage message){
        Validate.notNull(context);
        Validate.notNull(key);

        MessageLanguage language = message.getMessageLanguage();

        Map<String, String> languageMessages = getMessagesByLanguage(language);
        String oldMessage = getByKey(message)
                .get();

        if(getByKey(message).isPresent() && !context.isEmpty()){
            languageMessages.replace(key, oldMessage, context);
        }

        return !oldMessage.equalsIgnoreCase(getByKey(message).get());
    }

    public Option<String> getByKey(StandardMessage message){
        MessageLanguage language = message.getMessageLanguage();
        String key = message.getKey();

        return PandaStream.of(getMessagesByLanguage(language).entrySet())
                .find(o -> o.getKey().equalsIgnoreCase(key))
                .map(Map.Entry::getValue);
    }


}
