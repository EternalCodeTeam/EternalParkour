package net.eternalcode.eternalparkour.command.implementation;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;
import net.eternalcode.eternalparkour.user.User;
import net.eternalcode.eternalparkour.user.UserFactory;
import net.eternalcode.eternalparkour.user.UserManager;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import panda.std.Option;

import java.util.UUID;

@Section(route = "user", aliases = "usr")
public class UserCommand {

    private final UserManager userManager;

    public UserCommand(UserManager userManager) {
        this.userManager = userManager;
    }


    @Execute(route = "addXp")
    public void addUserExperience(Player player, double xp){
        Validate.isTrue(xp > 0);

        UUID uuid = player.getUniqueId();

        Option<User> userOption = userManager.getUser(uuid);

        if(userOption.isPresent()){
            User user = userOption.get();

            double exp = user.getXp();

            user.setXp(exp + xp);

        }
    }

    @Execute(route = "removeXp")
    public void removeUserExperience(Player player, double xp){
        UUID uuid = player.getUniqueId();

        Option<User> userOption = userManager.getUser(uuid);

        if(userOption.isPresent()){
            User user = userOption.get();


        }
    }

    @Execute(route = "setXp")
    public void setUserExperience(Player player, double xp){
        UUID uuid = player.getUniqueId();

        Option<User> userOption = userManager.getUser(uuid);

        if(userOption.isPresent()){
            User user = userOption.get();


        }
    }

}
