package net.eternalcode.eternalparkour.database;

import lombok.Getter;
import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.block.failblock.FailBlockDataRepository;
import net.eternalcode.eternalparkour.block.locationblock.ParkourBlockDataRepository;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.configuration.model.DatabaseConfigurationModel;
import net.eternalcode.eternalparkour.parkour.ParkourDataRepository;
import net.eternalcode.eternalparkour.score.UserScoreDataRepository;
import net.eternalcode.eternalparkour.user.UserDataRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class DatabaseManager {

    private final static String DBDRIVER = "com.mysql.jdbc.Driver";
    private final static Logger LOGGER = LogManager.getLogger(DatabaseManager.class);

    private final ParkourDataRepository parkourDataRepository = new ParkourDataRepository();
    private final UserDataRepository userDataRepository = new UserDataRepository();
    private final UserScoreDataRepository userScoreDataRepository = new UserScoreDataRepository();
    private final FailBlockDataRepository failBlockDataRepository = new FailBlockDataRepository();
    private final ParkourBlockDataRepository parkourBlockDataRepository = new ParkourBlockDataRepository();

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
                databaseConfigurationModel.url,
                databaseConfigurationModel.username,
                databaseConfigurationModel.password
        );

        if(connection != null) {
            LOGGER.info("Connection is established");
        }

        createTables();
    }


    private void createTables(){
        userDataRepository.createTable(connection);
        parkourDataRepository.createTable(connection);
        userScoreDataRepository.createTable(connection);
        parkourBlockDataRepository.createTable(connection);
        failBlockDataRepository.createTable(connection);
    }
}
