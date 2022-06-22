package net.eternalcode.eternalparkour.parkour;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import panda.std.Option;
import panda.std.Result;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collections;

public class ParkourFactory {

    private final ParkourManager parkourManager = EternalParkourPlugin.getPluginInstance()
            .getParkourManager();

    public Result<Parkour, Exception> createParkour(int id){
        Option<Parkour> parkourOption = parkourManager.getParkourById(id);

        if(parkourOption.isPresent()){
            return Result.error(new InstanceAlreadyExistsException("Already exists."));
        }

        return Result.ok(
                Parkour.builder()
                        .parkourId(id)
                        .parkourName("none")
                        .author("none")
                        .isOpen(false)
                        .endBlocks(new ArrayList<>())
                        .startBlocks(new ArrayList<>())
                        .failblocks(new ArrayList<>())
                        .build()
        );
    }

    public Result<Parkour, Exception> createParkour(String name){
        Option<Parkour> parkourOption = parkourManager.getParkourByName(name);

        if(parkourOption.isPresent()){
            return Result.error(new InstanceAlreadyExistsException("Already exists."));
        }

        return Result.<Parkour, Exception>ok(
                Parkour.builder()
                        .parkourId(0)
                        .parkourName(name)
                        .author("none")
                        .isOpen(false)
                        .endBlocks(new ArrayList<>())
                        .startBlocks(new ArrayList<>())
                        .failblocks(new ArrayList<>())
                        .build()
        ).peek(parkourManager::addParkour);
    }

    public Result<Parkour, Exception> removeParkour(int id){
        Option<Parkour> parkourOption = parkourManager.getParkourById(id);

        if(!parkourOption.isPresent()){
            return Result.error(new NullPointerException("Instance is a null."));
        }

        return Result.<Parkour, Exception>ok(parkourOption.get())
                .peek(parkourManager::removeParkour);
    }
}
