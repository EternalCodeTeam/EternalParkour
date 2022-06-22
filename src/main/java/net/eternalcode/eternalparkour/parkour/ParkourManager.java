package net.eternalcode.eternalparkour.parkour;

import panda.std.Option;
import panda.std.stream.PandaStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkourManager {
    private final List<Parkour> parkourList = new ArrayList<>();


    public List<Parkour> getParkourList() {
        return Collections.unmodifiableList(parkourList);
    }

    public void addParkour(Parkour parkour){
        if(!parkourList.contains(parkour))
            parkourList.add(parkour);
    }

    public void removeParkour(Parkour parkour){
        parkourList.remove(parkour);
    }

    public Option<Parkour> getParkourById(int id){
        return PandaStream.of(parkourList)
                .find(parkour -> parkour.getParkourId() == id);
    }

    public Option<Parkour> getParkourByName(String name){
        return PandaStream.of(parkourList)
                .find(parkour -> parkour.getParkourName().equalsIgnoreCase(name));
    }


}
