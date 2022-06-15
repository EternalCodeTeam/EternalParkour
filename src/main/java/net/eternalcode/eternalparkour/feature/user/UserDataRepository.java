package net.eternalcode.eternalparkour.feature.user;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDataRepository implements DataRepository<User, UUID> {

    private static final String TABLE_NAME = "users";
    private final DatabaseManager databaseManager = EternalParkourPlugin
            .getPluginInstance().getDatabaseManager();

    @SneakyThrows
    @Override
    public void update(User data) {
        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + "username = ?"
                + "uuid = ?"
                + "level = ?"
                + "xp = ?"
                + "coins = ?"
                + "where uuid = ?";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(updateQuery);

        statement.setString(1, data.getUsername());
        statement.setString(2, data.fromUUID());
        statement.setInt(3, data.getLevel());
        statement.setDouble(4, data.getXp());
        statement.setInt(5, data.getCoins());
        statement.setString(6, data.fromUUID());

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
                + " (uuid, level, xp, coins) "
                + " VALUES (?, ?, ?, ?)";

        PreparedStatement statement = databaseManager.getConnection()
                .prepareStatement(insertQuery);

        statement.setString(1, data.fromUUID());
        statement.setInt(2, data.getLevel());
        statement.setDouble(3, data.getXp());
        statement.setInt(4, data.getCoins());

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
                    .build();
        }

        rs.close();
        return user;
    }
}
