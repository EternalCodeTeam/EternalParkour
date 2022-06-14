package net.eternalcode.eternalparkour.command.base;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.scheme.Scheme;
import net.eternalcode.eternalparkour.util.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class InvalidCommandUsageHandler implements InvalidUsageHandler<CommandSender> {

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Scheme scheme) {
        List<String> schemes = scheme.getSchemes();

        if (schemes.size() == 1) {
            sender.sendMessage(ChatUtils.component("&cWrong command usage &8>> &7" + schemes.get(0)));
            return;
        }

        sender.sendMessage(ChatUtils.component("&cWrong command usage!"));
        for (String sch : schemes) {
            sender.sendMessage(ChatUtils.component("&8 >> &7" + sch));
        }
    }
}
