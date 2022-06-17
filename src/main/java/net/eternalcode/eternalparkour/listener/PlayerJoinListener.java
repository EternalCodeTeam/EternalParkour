package net.eternalcode.eternalparkour.listener;

import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.message.MessageSender;
import net.eternalcode.eternalparkour.message.StandardMessage;
import net.eternalcode.eternalparkour.user.User;
import net.eternalcode.eternalparkour.user.UserFactory;
import net.eternalcode.eternalparkour.user.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.management.InstanceAlreadyExistsException;
import java.util.UUID;

public class PlayerJoinListener implements Listener{

    private final EternalParkourPlugin plugin = EternalParkourPlugin.getPluginInstance();
    private final UserFactory userFactory = plugin.getUserFactory();
    private final UserManager userManager = plugin.getUserManager();


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();



        userFactory.createUser(uuid)
                .filter(user1 -> !userManager.getUser(user1.getUuid()).isPresent(), (user1) -> {
                    StandardMessage standardMessage = new StandardMessage("firstJoinMessage")
                            .withLanguage(user1.getChosenLanguage())
                            .withType(MessageSender.PLAYER);

                    standardMessage.printMessage(player);

                    return new InstanceAlreadyExistsException("");
                })
                .peek(userManager::addUser);
    }
}
