package net.eternalcode.eternalparkour.block.failblock;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import org.bukkit.Material;
import org.junit.internal.runners.statements.Fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FailBlockDataRepository implements DataRepository<FailBlock, Integer>{

    private static final String TABLE_NAME = "FailBlocks";
    private final DatabaseManager databaseManager = EternalParkourPlugin.getPluginInstance().getDatabaseManager();

    @SneakyThrows
    @Override
    public void update(FailBlock data) {
        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + "parkourID = ?,"
                + "failBlockID = ?,"
                + "material = ?"
                + "WHERE failBlockID = ?";

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(updateQuery);

        statement.setInt(1, data.getParkourId());
        statement.setInt(2, data.getFailBlockId());
        statement.setString(3, data.getMaterial().name());
        statement.setInt(4, data.getFailBlockId());

        statement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void delete(FailBlock data) {
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE failBlockID = ?";

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(deleteQuery);

        statement.setInt(1, data.getFailBlockId());

        statement.execute();
    }


    @SneakyThrows
    @Override
    public void insert(FailBlock data) {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (parkourID, failBlockID, material)"
                + " VALUES (?, ?, ?)";

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(insertQuery);

        statement.setInt(1, data.getParkourId());
        statement.setInt(2, data.getFailBlockId());
        statement.setString(3, data.getMaterial().name());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public FailBlock select(Integer id) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE failBlockID = ?";

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(selectQuery);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        FailBlock failBlock = null;

        while(rs.next()){
            failBlock = FailBlock.builder()
                            .failBlockId(rs.getInt("failBlockID"))
                            .parkourId(rs.getInt("parkourID"))
                            .material(Material.valueOf(rs.getString("material")))
                            .build();
        }

        rs.close();
        return failBlock;
    }

    @SneakyThrows
    @Override
    public List<FailBlock> selectAll() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(selectQuery);

        ResultSet rs = statement.executeQuery();

        List<FailBlock> failBlocks = new ArrayList<>();

        while(rs.next()){
            FailBlock failblock = FailBlock.builder()
                    .failBlockId(rs.getInt("failBlockID"))
                    .parkourId(rs.getInt("parkourID"))
                    .material(Material.valueOf(rs.getString("material")))
                    .build();

            failBlocks.add(failblock);
        }

        rs.close();
        return Collections.unmodifiableList(failBlocks);
    }

    @SneakyThrows
    @Override
    public void createTable(Connection connection) {
        String tableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " ("
                + "parkourID INT,"
                + "failBlockID INT PRIMARY KEY AUTOINCREMENT,"
                + "material VARCHAR(255)"
                + ")";

        Statement statement =
                connection.createStatement();

        statement.executeUpdate(tableQuery);
    }
}
