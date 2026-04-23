package sk.tuke.thinkbeforeclick.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.tuke.thinkbeforeclick.entity.AnswerLogEntity;

public interface AnswerLogRepo extends JpaRepository<AnswerLogEntity, Long> {

    @Transactional
    @Modifying
    @Query("delete from AnswerLogEntity a where a.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
