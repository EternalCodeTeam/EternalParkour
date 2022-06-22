package net.eternalcode.eternalparkour.block.locationblock;

import lombok.SneakyThrows;
import net.eternalcode.eternalparkour.EternalParkourPlugin;
import net.eternalcode.eternalparkour.database.DataRepository;
import net.eternalcode.eternalparkour.database.DatabaseManager;
import net.eternalcode.eternalparkour.util.LocationUtils;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkourBlockDataRepository implements DataRepository<LocationBlock, Integer> {
    private static final String TABLE_NAME = "ParkourBlocks";
    private final DatabaseManager databaseManager = EternalParkourPlugin.getPluginInstance().getDatabaseManager();

    @SneakyThrows
    @Override
    public void update(LocationBlock data) {
        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + "blockLocation = ?"
                + "blockType = ?"
                + "parkourID = ?"
                + "blockID = ?";

        PreparedStatement statement =
                databaseManager.getConnection().prepareStatement(updateQuery);

        statement.setString(1, LocationUtils.fromLocationForBlock(data.getBlockLocation()));
        statement.setString(2, data.getBlockType().name());
        statement.setInt(3, data.getParkourId());
        statement.setInt(4, data.getBlockId());

        statement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void delete(LocationBlock data) {
        String deleteQuery = "DELETE " + TABLE_NAME + " WHERE blockID = ?";

        PreparedStatement statement =
            databaseManager.getConnection().prepareStatement(deleteQuery);

        statement.setInt(1, data.getBlockId());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public void insert(LocationBlock data) {
        String insertQuery = "INSERT INTO" + TABLE_NAME
                + " (blockID, blockType, blockLocation, parkourID)"
                + " VALUES (?,?,?,?)";

        PreparedStatement statement
                = databaseManager.getConnection().prepareStatement(insertQuery);

        statement.setInt(1, data.getBlockId());
        statement.setString(2, data.getBlockType().name());
        statement.setString(3, LocationUtils.fromLocationForBlock(data.getBlockLocation()));
        statement.setInt(4, data.getParkourId());

        statement.execute();
    }

    @SneakyThrows
    @Override
    public LocationBlock select(Integer id) {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE blockID = ?";

        PreparedStatement statement
                = databaseManager.getConnection().prepareStatement(selectQuery);

        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();
        LocationBlock locationBlock = null;


        while(rs.next()){
            locationBlock = LocationBlock
                    .builder()
                    .parkourId(rs.getInt("parkourID"))
                    .blockId(rs.getInt("blockID"))
                    .blockLocation(LocationUtils.toBlockLocation("blockLocation"))
                    .blockType(BlockType.valueOf(rs.getString("blockType")))
                    .build();
        }

        rs.close();
        return locationBlock;
    }

    @SneakyThrows
    @Override
    public List<LocationBlock> selectAll() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        PreparedStatement statement
                = databaseManager.getConnection().prepareStatement(selectQuery);

        ResultSet rs = statement.executeQuery();
        List<LocationBlock> locationBlocks = new ArrayList<>();

        while(rs.next()){
            LocationBlock locationBlock = LocationBlock
                    .builder()
                    .parkourId(rs.getInt("parkourID"))
                    .blockId(rs.getInt("blockID"))
                    .blockLocation(LocationUtils.toBlockLocation("blockLocation"))
                    .blockType(BlockType.valueOf(rs.getString("blockType")))
                    .build();

            locationBlocks.add(locationBlock);
        }

        rs.close();
        return Collections.unmodifiableList(locationBlocks);
    }

    @SneakyThrows
    @Override
    public void createTable(Connection connection) {
        String tableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "blockLocation VARCHAR(255),"
                + "blockType VARCHAR(64),"
                + "blockID INT PRIMARY KEY,"
                + "parkourID INT" +
                ")";

        Statement statement = connection.createStatement();

        statement.executeUpdate(tableQuery);
    }
}
