package net.eternalcode.eternalparkour.block.failblock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

@Getter
@Setter
@Builder
public class FailBlock {
    private int parkourId;
    private int failBlockId;
    private Material material;
}
