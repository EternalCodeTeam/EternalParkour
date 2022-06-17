package net.eternalcode.eternalparkour.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.eternalcode.eternalparkour.message.MessageLanguage;

import java.util.UUID;


@Getter
@Setter
@Builder
@EqualsAndHashCode

public class User {

    private String username;

    private UUID uuid;

    private int level;

    private double xp;

    private int coins;

    private MessageLanguage chosenLanguage;

    public String fromUUID() {
        return uuid.toString();
    }

    public UUID toUUID() {
        return UUID.fromString(fromUUID());
    }

}
