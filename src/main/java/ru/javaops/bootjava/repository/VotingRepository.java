package ru.javaops.bootjava.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
@Tag(name = "Voting Controller")
public interface VotingRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.voteDate=MAX(v.voteDate)")
    Vote get(@Param("userId") int userId);

    @Query("SELECT MAX(v.voteDate) FROM Vote v WHERE v.user.id=:userId")
    LocalDateTime getLastVoteTime(@Param("userId") int userId);
}
