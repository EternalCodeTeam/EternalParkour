package net.eternalcode.eternalparkour.user;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.message.MessageLanguage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import panda.std.Option;
import panda.std.Result;

import javax.management.InstanceAlreadyExistsException;
import java.util.UUID;

public class UserFactory {

    private final UserManager userManager = EternalParkourPlugin.getPluginInstance().getUserManager();

    public Result<User, Exception> createUser(UUID uuid){

        Option<User> user = userManager.getUser(uuid);
        Player player =
                Option.of(Bukkit.getPlayer(uuid))
                        .filter(Player::isOnline)
                        .get();

        if(user.isPresent())
            return Result.error(new InstanceAlreadyExistsException("User is already created!"));

        return Result.ok(
                User.builder()
                        .uuid(uuid)
                        .level(1)
                        .xp(0)
                        .username(player.getName())
                        .chosenLanguage(MessageLanguage.EN)
                        .build()
        );
    }
}
