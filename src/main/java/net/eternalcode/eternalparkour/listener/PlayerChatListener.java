package net.eternalcode.eternalparkour.listener;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.user.User;
import net.eternalcode.eternalparkour.user.UserManager;
import net.eternalcode.eternalparkour.util.ChatUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

public class PlayerChatListener implements Listener {

    private final EternalParkourPlugin plugin = EternalParkourPlugin.getPluginInstance();
    private final ConfigurationManager configurationManager = plugin.getConfigurationManager();
    private final UserManager userManager = plugin.getUserManager();
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event){

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        User user = userManager.getUser(uuid)
                .get();

        ChatRenderer renderer = (source, sourceDisplayName, message, viewer) -> {
              String chatFormat = configurationManager.getChatConfiguration().chatFormatContext;

              String replacedString = chatFormat
                      .replaceAll("\\$\\(user-level\\)", String.valueOf(user.getLevel()))
                      .replaceAll("\\$\\(user-name\\)", user.getUsername())
                      .replaceAll("\\$\\(chat-message\\)", miniMessage.serialize(message));

              return ChatUtils.component(replacedString);
        };

        event.renderer(renderer);
    }

}
