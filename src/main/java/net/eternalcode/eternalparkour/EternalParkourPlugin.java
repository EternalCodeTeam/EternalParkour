package net.eternalcode.eternalparkour;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import lombok.Getter;
import net.eternalcode.eternalparkour.command.base.InvalidCommandUsageHandler;
import net.eternalcode.eternalparkour.command.base.MessageLanguageArgument;
import net.eternalcode.eternalparkour.command.base.PermissionMessageHandler;
import net.eternalcode.eternalparkour.command.implementation.ConfigurationCommand;
import net.eternalcode.eternalparkour.command.implementation.LanguageCommand;
import net.eternalcode.eternalparkour.configuration.ConfigurationManager;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import net.eternalcode.eternalparkour.listener.PlayerChatListener;
import net.eternalcode.eternalparkour.listener.PlayerJoinListener;
import net.eternalcode.eternalparkour.feature.task.UserUpdateTask;
import net.eternalcode.eternalparkour.message.MessageLanguage;
import net.eternalcode.eternalparkour.parkour.ParkourDatabaseManager;
import net.eternalcode.eternalparkour.parkour.ParkourFactory;
import net.eternalcode.eternalparkour.parkour.ParkourManager;
import net.eternalcode.eternalparkour.user.UserDatabaseManager;
import net.eternalcode.eternalparkour.user.UserFactory;
import net.eternalcode.eternalparkour.user.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import panda.std.stream.PandaStream;

@Getter
@Plugin(name = "EternalParkour", version = "1.0.0")
@ApiVersion(ApiVersion.Target.v1_19)
public class EternalParkourPlugin extends JavaPlugin {

    private static EternalParkourPlugin pluginInstance;


    private ConfigurationManager configurationManager;
    private LiteCommands<CommandSender> liteCommands;

    private UserManager userManager;
    private UserFactory userFactory;
    private UserDatabaseManager userDatabaseManager;

    private ParkourManager parkourManager;
    private ParkourFactory parkourFactory;
    private ParkourDatabaseManager parkourDatabaseManager;

    private DatabaseManager databaseManager;

    private UserUpdateTask userUpdateTask;

    @Override
    public void onDisable() {
        this.userDatabaseManager = new UserDatabaseManager();
        userDatabaseManager.saveUsers();
    }

    @Override
    public void onEnable() {
        pluginInstance = this;

        this.userManager = new UserManager();
        this.userFactory = new UserFactory();

        this.parkourManager = new ParkourManager();
        this.parkourFactory = new ParkourFactory();

        this.configurationManager = new ConfigurationManager();

        configurationManager.init();

        this.databaseManager = new DatabaseManager();
        databaseManager.prepareConnection();




        initializeListeners();
        initializeCommands();

        this.userDatabaseManager = new UserDatabaseManager();
        userDatabaseManager.loadUsers();

        this.parkourDatabaseManager = new ParkourDatabaseManager();

        this.userUpdateTask = new UserUpdateTask();
    }


    public static EternalParkourPlugin getPluginInstance() {
        return pluginInstance;
    }

    private void initializeListeners(){
        PandaStream.of(
                new PlayerJoinListener(),
                new PlayerChatListener()
        ).forEach(l ->
                this.getServer().getPluginManager().registerEvents(l, this));
    }

    private void initializeCommands(){
        this.liteCommands = LiteBukkitFactory.builder(getServer(), "")
                .argument(Player.class, new BukkitPlayerArgument(this.getServer(), "&cUser not found!"))

                .contextualBind(Player.class,
                        new BukkitOnlyPlayerContextual("&cYou have to be a player to use that command!!"))

                .invalidUsageHandler(new InvalidCommandUsageHandler())


                .permissionHandler(new PermissionMessageHandler())

                .typeBind(ConfigurationManager.class, () -> configurationManager)
                .typeBind(UserManager.class, () -> userManager)

                .command(
                        ConfigurationCommand.class,
                        LanguageCommand.class)

                .register();
    }
}
