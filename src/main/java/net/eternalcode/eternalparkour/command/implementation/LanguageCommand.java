package net.eternalcode.eternalparkour.command.implementation;

import dev.rollczi.litecommands.argument.option.Opt;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;
import net.eternalcode.eternalparkour.configuration.model.MessageConfigurationModel;
import net.eternalcode.eternalparkour.message.MessageLanguage;
import net.eternalcode.eternalparkour.message.MessageSender;
import net.eternalcode.eternalparkour.message.StandardMessage;
import net.eternalcode.eternalparkour.user.User;
import net.eternalcode.eternalparkour.user.UserManager;
import org.bukkit.World;
import org.bukkit.entity.Player;
import panda.std.Option;

import java.util.UUID;

@Section(route = "language", aliases = "lang")
public class LanguageCommand {

    private final StandardMessage changeLanguageMessage = new StandardMessage("changeLanguageMessage");
    private final UserManager userManager;

    public LanguageCommand(UserManager userManager) {
        this.userManager = userManager;
    }

    @Execute(route = "change")
    public void changeLanguage(Player player, @Opt Option<MessageLanguage> language){
        UUID uuid = player.getUniqueId();

        Option<User> userOption = userManager.getUser(uuid);

        if(userOption.isPresent()){
            User user = userOption.get();

            user.setChosenLanguage(language.get());
            changeLanguageMessage.setMessageLanguage(user.getChosenLanguage());
            changeLanguageMessage.setMessageType(MessageSender.PLAYER);

            changeLanguageMessage.printMessage(player);
        }
    }
}
