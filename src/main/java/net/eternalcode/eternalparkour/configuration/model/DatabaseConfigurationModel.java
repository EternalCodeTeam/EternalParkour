package net.eternalcode.eternalparkour.configuration.model;

import com.j256.ormlite.db.DatabaseType;
import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import net.eternalcode.eternalparkour.database.DatabaseObject;

import java.io.File;

public class DatabaseConfigurationModel extends AbstractConfigWithResource {
    public DatabaseConfigurationModel(File folder, String child) {
        super(folder, child);
    }

    @Description("Database configuration model for the database configuration")
    public DatabaseObject databaseObject = DatabaseObject
            .builder()
            .url("url")
            .username("username")
            .password("password")
            .build();


}
