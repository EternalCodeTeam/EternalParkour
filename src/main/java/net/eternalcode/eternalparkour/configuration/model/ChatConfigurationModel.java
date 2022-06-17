package net.eternalcode.eternalparkour.configuration.model;

import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ChatConfigurationModel extends AbstractConfigWithResource {
    private final static Logger LOGGER = LogManager.getLogger(ChatConfigurationModel.class);


    public ChatConfigurationModel(File folder, String child) {
        super(folder, child);

        LOGGER.info("Initializing chat configuration...");
    }


    @Description("it makes custom chat format when a person send a chat message.")
    @Description("$(user-level) - describes the persons level.")
    @Description("$(user-name) - the name of the person.")
    @Description("$(chat-message) - the message.")
    public String chatFormatContext = "&5$(user-level)&7Lvl. &8$(user-name): &f$(chat-message)";

}
