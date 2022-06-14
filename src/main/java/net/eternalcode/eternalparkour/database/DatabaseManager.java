package net.eternalcode.eternalparkour.database;

import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.configuration.model.DatabaseConfigurationModel;

import java.sql.SQLException;

public class DatabaseManager {
    private String connectionUrl;
    private String username;
    private String password;

    private ConfigurationManager configurationManager
            = EternalParkourPlugin
            .getPluginInstance().getConfigurationManager();



    private ConnectionSource connectionSource;

    public void initDatabase() throws SQLException {
        DatabaseConfigurationModel databaseConfigurationModel = configurationManager
                .getDatabaseConfiguration();


        this.connectionSource =
                new JdbcConnectionSource(
                        databaseConfigurationModel.databaseObject
                                .getUrl(),
                        databaseConfigurationModel.databaseObject
                                .getUsername(),
                        databaseConfigurationModel.databaseObject
                                .getPassword()
                );

    }
}
