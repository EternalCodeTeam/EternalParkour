package net.eternalcode.eternalparkour.feature.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder
public class User {
    private UUID uuid;
    private String username;
    private int level;
    private double xp;
}
