package sk.tuke.thinkbeforeclick.controller;

import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.entity.ScoreEntity;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.ScoreRepo;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class GameController {

    private final UserRepo userRepo;
    private final ScoreRepo scoreRepo;

    public GameController(UserRepo userRepo, ScoreRepo scoreRepo) {
        this.userRepo = userRepo;
        this.scoreRepo = scoreRepo;
    }

    @PostMapping("/result")
    public void saveResult(@RequestBody ResultRequest req) {
        String username = req.username == null ? "" : req.username.trim();
        if (username.isEmpty()) throw new RuntimeException("Username empty");

        UserEntity user = userRepo.findByUsername(username)
                .orElseGet(() -> userRepo.save(new UserEntity(username)));

        // pokus = hra spustená/dokončená
        user.incrementGamesPlayed();
        userRepo.save(user);

        // uložiť výsledok (percentá)
        scoreRepo.save(new ScoreEntity(user, req.successPercent));
    }


    public static class ResultRequest {
        public String username;
        public int successPercent;
    }

    public record LeaderboardRow(String name, int successPercent, int attempts) {}
}