package net.eternalcode.eternalparkour.parkour;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.eternalcode.eternalparkour.score.UserScore;
import org.bukkit.Location;

import java.util.List;

@Getter
@Setter
@Builder
public class Parkour {
    private int parkourId;
    private String parkourName;
    private String author;
    private boolean isOpen;
    private List<String> failblocks;
    private List<Location> startBlocks;
    private List<Location> endBlocks;
    private List<UserScore> scores;

}
