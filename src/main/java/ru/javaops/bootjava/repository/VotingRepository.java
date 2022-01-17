package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDateTime;

public interface VotingRepository extends BaseRepository<Vote> {

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote u WHERE u.user.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT MAX(v.voteDate) FROM Vote v WHERE v.user.id=:userId")
    LocalDateTime getLastVoteTime(@Param("userId") int userId);
}
