package net.eternalcode.eternalparkour.block.locationblock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
@Builder
public class LocationBlock {
    private Location blockLocation;
    private BlockType blockType;
    private int parkourId;
    private int blockId;
}
