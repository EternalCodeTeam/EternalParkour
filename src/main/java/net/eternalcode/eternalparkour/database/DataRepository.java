package net.eternalcode.eternalparkour.database;

import java.sql.Connection;
import java.util.List;

public interface DataRepository<T, I> {
    void update(T data);

    void delete(T data);

    void insert(T data);

    T select(I id);

    List<T> selectAll();

    void createTable(Connection connection);
}
