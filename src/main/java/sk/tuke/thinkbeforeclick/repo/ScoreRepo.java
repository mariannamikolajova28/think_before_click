package sk.tuke.thinkbeforeclick.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.tuke.thinkbeforeclick.entity.ScoreEntity;

import java.util.List;

public interface ScoreRepo extends JpaRepository<ScoreEntity, Long> {

    @Query("""
    SELECT new sk.tuke.thinkbeforeclick.controller.LeaderboardRow(
        u.username,
        COALESCE(MAX(s.successPercent), 0),
        u.gamesPlayed
    )
    FROM UserEntity u
    LEFT JOIN ScoreEntity s ON s.user = u
    GROUP BY u.username, u.gamesPlayed
    ORDER BY COALESCE(MAX(s.successPercent), 0) DESC
""")
    List<sk.tuke.thinkbeforeclick.controller.LeaderboardRow> leaderboard();

    @Transactional
    @Modifying
    @Query("delete from ScoreEntity s where s.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}