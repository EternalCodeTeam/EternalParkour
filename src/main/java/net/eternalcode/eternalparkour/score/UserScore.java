package net.eternalcode.eternalparkour.score;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@Setter
public class UserScore {

    private UUID uuid;
    private long time;
    private String username;
    private Date topDate;
}
