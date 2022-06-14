package net.eternalcode.eternalparkour.command.base;

import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.permission.LitePermissions;
import dev.rollczi.litecommands.handle.PermissionHandler;
import net.eternalcode.eternalparkour.util.ChatUtils;
import org.bukkit.command.CommandSender;

public class PermissionMessageHandler implements PermissionHandler<CommandSender> {

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, LitePermissions litePermissions) {
        sender.sendMessage(ChatUtils.component("&cAccess denied! &7(" + String.join(", ", litePermissions.getPermissions()) + ")"));
    }

}
