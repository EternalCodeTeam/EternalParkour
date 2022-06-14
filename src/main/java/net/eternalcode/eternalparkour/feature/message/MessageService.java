package net.eternalcode.eternalparkour.feature.message;

import io.reactivex.rxjava3.core.Maybe;
import net.eternalcode.eternalparkour.configuration.AbstractConfigWithResource;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class MessageService {



    public <T extends AbstractConfigWithResource> void constructMessage(StandardMessage message, CommandSender sender, T config){
        MessageLanguage language = message.getMessageLanguage();
        MessageType messageType = message.getMessageType();

        File messagesFile = new File(language.getFileName());

        Maybe<?> whoSentMaybe = (Maybe<?>) Maybe.just(sender)
                .filter(sender1 -> messageType == MessageType.PLAYER)
                .map(Player.class::cast)

                .filter(sender1 -> messageType == MessageType.CONSOLE)
                .map(CommandSender.class::cast)

                .subscribe(sender1 -> );
    }
}
