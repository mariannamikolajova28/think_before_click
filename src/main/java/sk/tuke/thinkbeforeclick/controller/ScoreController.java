package sk.tuke.thinkbeforeclick.controller;

import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.Score;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin
public class ScoreController {

    private List<Score> scores = new ArrayList<>();

    @PostMapping
    public void addScore(@RequestBody Score score) {
        scores.add(score);
    }

    @GetMapping
    public List<Score> getScores() {
        return scores;
    }
}