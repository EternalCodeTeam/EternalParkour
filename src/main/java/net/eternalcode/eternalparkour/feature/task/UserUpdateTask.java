package net.eternalcode.eternalparkour.feature.task;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.message.StandardMessage;
import net.eternalcode.eternalparkour.user.User;
import net.eternalcode.eternalparkour.user.UserDataRepository;
import net.eternalcode.eternalparkour.user.UserDatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class UserUpdateTask extends BukkitRunnable {

    private final UserDataRepository userDataRepository = new UserDataRepository();
    private final List<User> localUsers = EternalParkourPlugin
            .getPluginInstance()
            .getUserManager()
            .getUsers();

    private final UserDatabaseManager userDatabaseManager = EternalParkourPlugin
            .getPluginInstance().getUserDatabaseManager();

    private final Logger logger = LogManager.getLogger(StandardMessage.class);

    public UserUpdateTask(){
        runTaskTimerAsynchronously(EternalParkourPlugin.getPluginInstance(),
                1L, 20L * 60 * 10);
    }


    @Override
    public void run() {
        userDatabaseManager.saveUsers();
    }
}
