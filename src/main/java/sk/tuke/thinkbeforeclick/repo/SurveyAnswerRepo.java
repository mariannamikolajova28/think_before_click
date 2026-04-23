package sk.tuke.thinkbeforeclick.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sk.tuke.thinkbeforeclick.entity.SurveyAnswerEntity;

import java.util.Optional;

public interface SurveyAnswerRepo extends JpaRepository<SurveyAnswerEntity, Long> {

    // načíta posledný uložený dotazník daného používateľa (ak by si ukladala viac pokusov)
    Optional<SurveyAnswerEntity> findTopByUser_IdOrderByIdDesc(Long userId);

    @Modifying
    @Query("delete from SurveyAnswerEntity a where a.user.id = :userId")


    void deleteByUserId(@Param("userId") Long userId);

}