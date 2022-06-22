package net.eternalcode.eternalparkour.score;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import org.apache.commons.lang.Validate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserScoreDataRepository implements DataRepository<UserScore, UUID> {

    private static final String TABLE_NAME = "scores";
    private final DatabaseManager databaseManager = EternalParkourPlugin
            .getPluginInstance().getDatabaseManager();


    @SneakyThrows
    @Override
    public void update(UserScore data) {
        String updateQuery = "UPDATE " + TABLE_NAME
                + " SET "
                + "uuid = ?,"
                + "time = ?,"
                + "username = ?,"
                + "topDate = ?"
                + "WHERE uuid = ?";

        PreparedStatement statement =
                databaseManager
                        .getConnection().prepareStatement(updateQuery);

        statement.setString(1, data.getUuid().toString());
        statement.setLong(2, data.getTime());
        statement.setString(3, data.getUsername());
        statement.setDate(4, (Date) data.getTopDate());
    }

    @SneakyThrows
    @Override
    public void delete(UserScore data) {
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE uuid = ?";


        PreparedStatement statement =
                databaseManager
                        .getConnection().prepareStatement(deleteQuery);

        statement.setString(1, data.getUuid().toString());
    }

    @SneakyThrows
    @Override
    public void insert(UserScore data) {
        String insertQuery = "INSERT INTO " + TABLE_NAME
                + " (uuid, time, username, topDate)"
                + " VALUES (?,?,?,?)";


        PreparedStatement statement =
                databaseManager
                        .getConnection().prepareStatement(insertQuery);

        statement.setString(1, data.getUuid().toString());
        statement.setLong(2, data.getTime());
        statement.setString(3, data.getUsername());
        statement.setDate(4, (Date) data.getTopDate());
    }

    @SneakyThrows
    @Override
    public UserScore select(UUID id) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE uuid = ?";

        PreparedStatement statement
                = databaseManager
                .getConnection().prepareStatement(selectQuery);

        statement.setString(1, id.toString());

        ResultSet resultSet = statement.executeQuery();

        UserScore userScore = null;

        while(resultSet.next()){
            userScore = UserScore
                    .builder()
                    .uuid(UUID.fromString(resultSet.getString("uuid")))
                    .username(resultSet.getString("username"))
                    .time(resultSet.getLong("time"))
                    .topDate(resultSet.getDate("topDate"))
                    .build();
        }

        resultSet.close();
        return userScore;
    }

    @SneakyThrows
    @Override
    public List<UserScore> selectAll() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        List<UserScore> scores = new ArrayList<>();

        PreparedStatement statement
                = databaseManager
                .getConnection().prepareStatement(selectQuery);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            UserScore userScore = UserScore
                    .builder()
                    .uuid(UUID.fromString(resultSet.getString("uuid")))
                    .username(resultSet.getString("username"))
                    .time(resultSet.getLong("time"))
                    .topDate(resultSet.getDate("topDate"))
                    .build();

            scores.add(userScore);
        }

        resultSet.close();
        return scores;
    }


    @SneakyThrows
    @Override
    public void createTable(Connection connection) {
        Validate.notNull(connection);

        String tableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "("
                + "uuid VARCHAR(64) NOT NULL PRIMARY KEY,"
                + "username VARCHAR(255),"
                + "time LONG,"
                + "topDate DATETIME"
                + ");";

        Statement statement =
                connection.createStatement();

        statement.executeUpdate(tableQuery);
    }
}
