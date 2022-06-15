package net.eternalcode.eternalparkour.feature.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder

public class User {

    private String username;

    private UUID uuid;

    private int level;

    private double xp;

    private int coins;

    public String fromUUID() {
        return uuid.toString();
    }

    public UUID toUUID() {
        return UUID.fromString(fromUUID());
    }

}
