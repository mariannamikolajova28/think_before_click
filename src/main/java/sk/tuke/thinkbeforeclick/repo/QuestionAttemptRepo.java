package sk.tuke.thinkbeforeclick.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.tuke.thinkbeforeclick.entity.QuestionAttemptEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionAttemptRepo extends JpaRepository<QuestionAttemptEntity, Long> {

    interface SessionSummary {
        String getSessionId();
        LocalDateTime getStartedAt();
        long getTotal();
        long getCorrect();
    }

    @Query("""
        select a.gameSessionId as sessionId,
               min(a.answeredAt) as startedAt,
               count(a) as total,
               sum(case when a.correct = true then 1 else 0 end) as correct
        from QuestionAttemptEntity a
        where a.user.username = :username
        group by a.gameSessionId
        order by min(a.answeredAt) desc
    """)
    List<SessionSummary> findSessionSummaries(@Param("username") String username);

    List<QuestionAttemptEntity> findByUser_UsernameAndGameSessionIdOrderByAnsweredAtAsc(String username, String gameSessionId);

    @Transactional
    @Modifying
    @Query("delete from QuestionAttemptEntity q where q.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}