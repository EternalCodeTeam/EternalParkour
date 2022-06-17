package net.eternalcode.eternalparkour.score;

import net.eternalcode.eternalparkour.database.DataRepository;

import java.util.List;
import java.util.UUID;

public class UserScoreDataRepository implements DataRepository<UserScore, UUID> {
    @Override
    public void update(UserScore data) {

    }

    @Override
    public void delete(UserScore data) {

    }

    @Override
    public void insert(UserScore data) {

    }

    @Override
    public UserScore select(UUID id) {
        return null;
    }

    @Override
    public List<UserScore> selectAll() {
        return null;
    }

    @Override
    public void createTable() {

    }
}
