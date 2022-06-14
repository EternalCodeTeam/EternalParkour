package net.eternalcode.eternalparkour.database;

import com.j256.ormlite.db.DatabaseType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DatabaseObject {

    private String username;
    private String password;
    private String url;
}
