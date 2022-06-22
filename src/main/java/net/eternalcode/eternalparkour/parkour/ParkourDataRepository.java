package net.eternalcode.eternalparkour.parkour;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import org.apache.commons.lang.Validate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkourDataRepository implements DataRepository<Parkour, Integer> {

    private static final String TABLE_NAME = "parkours";
    private final DatabaseManager databaseManager = EternalParkourPlugin
            .getPluginInstance().getDatabaseManager();

    @SneakyThrows
    @Override
    public void update(Parkour data) {
        String updateQuery = "UPDATE " + TABLE_NAME +
                " SET "
                + "parkourId = ?"
                + "parkourName = ?"
                + "author = ?"
                + "isOpen = ?"
                + "WHERE parkourID = ?";

        PreparedStatement statement =
                databaseManager.getConnection()
                        .prepareStatement(updateQuery);

        statement.setInt(1, data.getParkourId());
        statement.setString(2, data.getParkourName());
        statement.setString(3, data.getAuthor());
        statement.setString(4, Boolean.toString(data.isOpen()));

        statement.execute();
    }

    @SneakyThrows
    @Override
    public void delete(Parkour data) {
        String deleteQuery = "DELETE " + TABLE_NAME + " WHERE parkourId = ?";

        PreparedStatement statement =
                databaseManager.getConnection()
                        .prepareStatement(deleteQuery);

        statement.setInt(1, data.getParkourId());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public void insert(Parkour data) {
        String insertQuery = "INSERT INTO" + TABLE_NAME
                + " (parkourId, parkourName, author, isOpen) "+
                " VALUES (?, ?, ?, ?)";

        PreparedStatement statement =
                databaseManager.getConnection()
                        .prepareStatement(insertQuery);

        statement.setInt(1, data.getParkourId());
        statement.setString(2, data.getParkourName());
        statement.setString(3, data.getAuthor());
        statement.setString(4, Boolean.toString(data.isOpen()));

        statement.execute();
    }

    @SneakyThrows
    @Override
    public Parkour select(Integer id) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE parkourId = ?";

        PreparedStatement statement =
                databaseManager.getConnection()
                        .prepareStatement(selectQuery);

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        Parkour parkour = null;

        while(resultSet.next()){
            parkour = Parkour.builder()
                    .parkourId(resultSet.getInt("parkourId"))
                    .parkourName(resultSet.getString("parkourName"))
                    .author(resultSet.getString("author"))
                    .isOpen(Boolean.parseBoolean(resultSet.getString("isOpen")))
                    .build();


            resultSet.close();
        }

        return parkour;
    }

    @SneakyThrows
    @Override
    public List<Parkour> selectAll() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        List<Parkour> parkourList = new ArrayList<>();

        PreparedStatement statement =
                databaseManager.getConnection()
                        .prepareStatement(selectQuery);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()){
            Parkour parkour = Parkour.builder()
                    .parkourId(resultSet.getInt("parkourId"))
                    .parkourName(resultSet.getString("parkourName"))
                    .author(resultSet.getString("author"))
                    .isOpen(Boolean.parseBoolean(resultSet.getString("isOpen")))
                    .build();

            parkourList.add(parkour);
            resultSet.close();
        }


        return Collections.unmodifiableList(parkourList);
    }

    @SneakyThrows
    @Override
    public void createTable(Connection connection) {
        Validate.notNull(connection);

        String tableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "("
                + "parkourId INTEGER PRIMARY KEY,"
                + "parkourName VARCHAR(255),"
                + "author VARCHAR(128),"
                + "isOpen VARCHAR(5)"
                + ");";

        Statement statement =
                connection.createStatement();

        statement.executeUpdate(tableQuery);
    }
}
