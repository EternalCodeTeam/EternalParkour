package net.eternalcode.eternalparkour.user;

import panda.std.Option;
import panda.std.stream.PandaStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserManager {

    private final List<User> users = new ArrayList<>();


    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public void addUser(User user){
        if(!users.contains(user)){
            users.add(user);
        }
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public Option<User> getUser(UUID uuid){
        return PandaStream.of(
                users
        ).find(user -> user.getUuid().equals(uuid));
    }


}
