package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.tuke.thinkbeforeclick.entity.QuestionAttemptEntity;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.QuestionAttemptRepo;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QuestionAttemptController {

    private final UserRepo userRepo;
    private final QuestionAttemptRepo attemptRepo;

    public QuestionAttemptController(UserRepo userRepo, QuestionAttemptRepo attemptRepo) {
        this.userRepo = userRepo;
        this.attemptRepo = attemptRepo;
    }

    @PostMapping("/attempt")
    public void saveAttempt(@RequestBody AttemptRequest req) {
        String username = req.username == null ? "" : req.username.trim();
        if (username.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username empty");

        if (req.imagePath == null || req.imagePath.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "imagePath empty");

        if (req.userAnswer == null || req.userAnswer.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userAnswer empty");

        if (req.gameSessionId == null || req.gameSessionId.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "gameSessionId empty");

        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        QuestionAttemptEntity entity = new QuestionAttemptEntity(
                user,
                req.imagePath.trim(),
                req.userAnswer.trim(),
                req.correct,
                req.gameSessionId.trim()
        );

        attemptRepo.save(entity);
    }

    public static class AttemptRequest {
        public String username;
        public String imagePath;
        public String userAnswer;   // "P" alebo "R"
        public boolean correct;
        public String gameSessionId;
    }
}
