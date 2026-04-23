package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserDeleteController {

    private final UserRepo userRepo;
    private final ScoreRepo scoreRepo;
    private final SurveyAnswerRepo surveyAnswerRepo;
    private final QuestionAttemptRepo questionAttemptRepo;
    private final AnswerLogRepo answerLogRepo;

    public UserDeleteController(
            UserRepo userRepo,
            ScoreRepo scoreRepo,
            SurveyAnswerRepo surveyAnswerRepo,
            QuestionAttemptRepo questionAttemptRepo,
            AnswerLogRepo answerLogRepo

    ) {
        this.userRepo = userRepo;
        this.scoreRepo = scoreRepo;
        this.surveyAnswerRepo = surveyAnswerRepo;
        this.questionAttemptRepo = questionAttemptRepo;
        this.answerLogRepo = answerLogRepo;

    }

    @DeleteMapping("/users/{username}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable String username) {

        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Long userId = userOpt.get().getId();

        // zmaž všetko, čo sa viaže na používateľa
        questionAttemptRepo.deleteByUserId(userId);
        scoreRepo.deleteByUserId(userId);
        surveyAnswerRepo.deleteByUserId(userId);
        answerLogRepo.deleteByUserId(userId);

        // nakoniec zmaž používateľa
        userRepo.deleteById(userId);

        return ResponseEntity.ok().build();
    }
}