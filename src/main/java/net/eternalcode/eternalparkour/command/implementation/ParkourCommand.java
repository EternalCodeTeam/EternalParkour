package net.eternalcode.eternalparkour.command.implementation;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;

@Section(route = "parkour", aliases = "pk")
public class ParkourCommand {

    @Execute(route = "create")
    public void createParkour(int id){

    }

    @Execute(route = "create")
    public void createParkour(String name){

    }
}
