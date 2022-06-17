package net.eternalcode.eternalparkour.configuration.model;

import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DatabaseConfigurationModel extends AbstractConfigWithResource {
    private final static Logger LOGGER = LogManager.getLogger(DatabaseConfigurationModel.class);


    public DatabaseConfigurationModel(File folder, String child) {
        super(folder, child);

        LOGGER.info("initializing database configuration...");
    }

    @Description("Database configuration model for the database configuration")

    public String url = "url";

    public String username = "username";

    public String password = "password";
}
