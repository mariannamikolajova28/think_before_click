package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sk.tuke.thinkbeforeclick.entity.SurveyAnswerEntity;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.SurveyAnswerRepo;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SurveyController {

    private final UserRepo userRepo;
    private final SurveyAnswerRepo surveyAnswerRepo;

    public SurveyController(UserRepo userRepo, SurveyAnswerRepo surveyAnswerRepo) {
        this.userRepo = userRepo;
        this.surveyAnswerRepo = surveyAnswerRepo;
    }

    // načítanie dotazníka pre daného usera
    @GetMapping("/survey/{username}")
    public ResponseEntity<?> getSurvey(@PathVariable String username) {
        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        Long userId = userOpt.get().getId();

        Optional<SurveyAnswerEntity> ansOpt =
                surveyAnswerRepo.findTopByUser_IdOrderByIdDesc(userId);

        return ansOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update (alebo vytvorenie) dotazníka
    @PutMapping("/survey/{username}")
    @Transactional
    public ResponseEntity<?> updateSurvey(@PathVariable String username,
                                          @RequestBody SurveyAnswerEntity incoming) {

        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        UserEntity user = userOpt.get();
        Long userId = user.getId();

        // nájdi posledný dotazník, ak neexistuje, vytvor nový
        SurveyAnswerEntity entity = surveyAnswerRepo
                .findTopByUser_IdOrderByIdDesc(userId)
                .orElseGet(() -> {
                    SurveyAnswerEntity e = new SurveyAnswerEntity();
                    e.setUser(user);
                    return e;
                });

        // nastav user (pre istotu) + hodnoty
        entity.setUser(user);
        entity.setVek(incoming.getVek());
        entity.setPohlavie(incoming.getPohlavie());
        entity.setVzdelanie(incoming.getVzdelanie());
        entity.setOdbor(incoming.getOdbor());
        entity.setSocialneSiete(incoming.getSocialneSiete());
        entity.setSkolenie(incoming.getSkolenie());
        entity.setBezpecnost(incoming.getBezpecnost());
        entity.setPhishing(incoming.getPhishing());

        surveyAnswerRepo.save(entity);

        return ResponseEntity.ok().build();
    }
}