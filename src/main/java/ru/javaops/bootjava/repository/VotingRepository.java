package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

public interface VotingRepository extends BaseRepository<Vote> {

    @Override
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote u WHERE u.user.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT MAX(v.voteDate) from Vote v where v.user.id=:userId")
    Vote getLastVote(@Param("userId") int userId);
}
