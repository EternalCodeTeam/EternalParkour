package net.eternalcode.eternalparkour.database;

public interface DataRepository<T, I> {
    void update(T data);

    void delete(T data);

    void insert(T data);

    T select(I id);
}
