package net.eternalcode.eternalparkour.database;

import lombok.Getter;
import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.configuration.model.DatabaseConfigurationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class DatabaseManager {

    private final static String DBDRIVER = "com.mysql.jdbc.Driver";
    private final static Logger LOGGER = LogManager.getLogger(DatabaseManager.class);

    private Connection connection;

    private final ConfigurationManager configurationManager
            = EternalParkourPlugin
            .getPluginInstance().getConfigurationManager();

    private final DatabaseConfigurationModel databaseConfigurationModel
            = configurationManager.getDatabaseConfiguration();


    @SneakyThrows
    public void prepareConnection() {
        Class.forName(DBDRIVER).getConstructor().newInstance();


        this.connection = DriverManager.getConnection(
                databaseConfigurationModel.databaseObject
                        .getUrl(),
                databaseConfigurationModel.databaseObject
                        .getUsername(),
                databaseConfigurationModel
                        .databaseObject
                        .getPassword()
        );

        if(connection != null) {
            LOGGER.info("Connection is established");
        }
    }
}
