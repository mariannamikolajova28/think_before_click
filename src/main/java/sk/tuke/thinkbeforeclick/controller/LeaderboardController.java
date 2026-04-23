package sk.tuke.thinkbeforeclick.controller;

import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.repo.ScoreRepo;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LeaderboardController {

    private final ScoreRepo scoreRepo;

    public LeaderboardController(ScoreRepo scoreRepo) {
        this.scoreRepo = scoreRepo;
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardRow> leaderboard() {
        return scoreRepo.leaderboard();
    }
}
