package net.eternalcode.eternalparkour.user;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import net.eternalcode.eternalparkour.message.MessageLanguage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserDataRepository implements DataRepository<User, UUID> {

    private static final String TABLE_NAME = "users";
    private final DatabaseManager databaseManager = EternalParkourPlugin
            .getPluginInstance().getDatabaseManager();

    @SneakyThrows
    @Override
    public void update(User data) {
        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + "username = ?, "
                + "uuid = ?, "
                + "level = ?, "
                + "xp = ?, "
                + "coins = ?, "
                + "language = ? "
                + "WHERE uuid = ?";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(updateQuery);

        statement.setString(1, data.getUsername());
        statement.setString(2, data.fromUUID());
        statement.setInt(3, data.getLevel());
        statement.setDouble(4, data.getXp());
        statement.setInt(5, data.getCoins());
        statement.setString(6, data.getChosenLanguage().name());
        statement.setString(7, data.fromUUID());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public void delete(User data) {
        String deleteQuery = "DELETE FROM" + TABLE_NAME + " WHERE uuid = ?";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(deleteQuery);

        statement.setString(1, data.fromUUID());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public void insert(User data) {
        String insertQuery = "INSERT INTO " + TABLE_NAME
                + " (uuid, level, xp, coins, username, language) "
                + " VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(insertQuery);

        statement.setString(1, data.fromUUID());
        statement.setInt(2, data.getLevel());
        statement.setDouble(3, data.getXp());
        statement.setInt(4, data.getCoins());
        statement.setString(5, data.getUsername());
        statement.setString(6, data.getChosenLanguage().name());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public User select(UUID uuid) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE uuid = ?";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(selectQuery);
        statement.setString(1, uuid.toString());

        ResultSet rs = statement.executeQuery();
        User user = null;

        while (rs.next()) {
            user = User.builder()
                    .uuid(UUID.fromString(rs.getString("uuid")))
                    .coins(rs.getInt("coins"))
                    .level(rs.getInt("level"))
                    .username(rs.getString("username"))
                    .xp(rs.getDouble("xp"))
                    .chosenLanguage(Enum.valueOf(MessageLanguage.class, rs.getString("language")))
                    .build();
        }

        rs.close();
        return user;
    }

    @SneakyThrows
    @Override
    public List<User> selectAll() {
        String selectQuery = "SELECT * FROM users";

        List<User> users = new ArrayList<>();

        PreparedStatement statement = databaseManager
                .getConnection()
                .prepareStatement(selectQuery);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            User user = User.builder()
                    .uuid(UUID.fromString(rs.getString("uuid")))
                    .coins(rs.getInt("coins"))
                    .level(rs.getInt("level"))
                    .username(rs.getString("username"))
                    .xp(rs.getDouble("xp"))
                    .chosenLanguage(MessageLanguage.valueOf(rs.getString("language")))
                    .build();

            users.add(user);
        }

        rs.close();
        return Collections.unmodifiableList(users);
    }

    @SneakyThrows
    @Override
    public void createTable() {
        String tableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
                "(" +
                "uuid varchar(255) NOT NULL," +
                "coins INT NOT NULL," +
                "level INT NOT NULL," +
                "username varchar(255) NOT NULL," +
                "xp DOUBLE NOT NULL," +
                "language varchar(255) NOT NULL," +
                "PRIMARY KEY (uuid)"
                + ")";

        PreparedStatement statement = databaseManager
                .getConnection()
                .prepareStatement(tableQuery);

        statement.execute();
    }
}
