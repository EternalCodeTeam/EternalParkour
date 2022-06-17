package net.eternalcode.eternalparkour.parkour;

import net.eternalcode.eternalparkour.score.UserScore;
import org.bukkit.Location;

import java.util.List;

public class Parkour {
    private int parkourId;
    private String parkourName;
    private String author;
    private List<String> failblocks;
    private List<Location> startBlocks;
    private List<Location> endBlocks;
    private List<UserScore> scores;

}
