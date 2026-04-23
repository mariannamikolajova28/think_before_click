package sk.tuke.thinkbeforeclick.controller;

import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.entity.QuestionAttemptEntity;
import sk.tuke.thinkbeforeclick.repo.QuestionAttemptRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AttemptHistoryController {

    private final QuestionAttemptRepo attemptRepo;

    public AttemptHistoryController(QuestionAttemptRepo attemptRepo) {
        this.attemptRepo = attemptRepo;
    }

    // 1) zoznam pokusov (sessions) pre používateľa
    @GetMapping("/attempt-sessions/{username}")
    public List<AttemptSessionDto> getSessions(@PathVariable String username) {
        return attemptRepo.findSessionSummaries(username.trim()).stream().map(s -> {
            int percent = (s.getTotal() <= 0) ? 0 : (int) Math.round((s.getCorrect() * 100.0) / s.getTotal());
            return new AttemptSessionDto(
                    s.getSessionId(),
                    s.getStartedAt(),
                    s.getTotal(),
                    s.getCorrect(),
                    percent
            );
        }).toList();
    }

    // 2) detail konkrétneho pokusu (všetky otázky/obrázky)
    @GetMapping("/attempt-details/{username}/{sessionId}")
    public List<AttemptDetailDto> getAttemptDetails(@PathVariable String username,
                                                    @PathVariable String sessionId) {

        List<QuestionAttemptEntity> items =
                attemptRepo.findByUser_UsernameAndGameSessionIdOrderByAnsweredAtAsc(username.trim(), sessionId);

        return items.stream().map(a -> new AttemptDetailDto(
                a.getImagePath(),
                a.getUserAnswer(),
                a.isCorrect(),
                a.getAnsweredAt()
        )).toList();
    }

    public record AttemptSessionDto(
            String sessionId,
            LocalDateTime startedAt,
            long total,
            long correct,
            int successPercent
    ) {}

    public record AttemptDetailDto(
            String imagePath,
            String userAnswer,
            boolean correct,
            LocalDateTime answeredAt
    ) {}
}