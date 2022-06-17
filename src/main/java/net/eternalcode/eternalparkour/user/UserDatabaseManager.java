package net.eternalcode.eternalparkour.user;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.message.StandardMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserDatabaseManager {
    private final UserDataRepository userDataRepository = new UserDataRepository();
    private final List<User> users = userDataRepository.selectAll();
    private final UserManager userManager = EternalParkourPlugin
            .getPluginInstance()
            .getUserManager();

    private final Logger logger = LogManager.getLogger(StandardMessage.class);



    public void loadUsers(){
        List<User> localUsers = userManager.getUsers();

        
        if(users.size() > 0) {
            users.forEach(userManager::addUser);

            logger.info("Loaded users to local database...");
            logger.info("--------------------------------");
            logger.info("Users count: " + localUsers.size());
        }
    }

    public void saveUsers(){
        List<User> users = userManager.getUsers();

        if(users.size() > 0) {
            logger.info("saved users to database...");
            logger.info("--------------------------------");
            logger.info("Users count: " + users.size());

            users.forEach(user -> {
                User selectedUser = userDataRepository.select(user.getUuid());

                if(selectedUser != null) {
                    userDataRepository.update(user);
                }else userDataRepository.insert(user);
            });
        }
    }
}
