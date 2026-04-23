package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.tuke.thinkbeforeclick.entity.SurveyAnswerEntity;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.SurveyAnswerRepo;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterController {

    private final UserRepo userRepo;
    private final SurveyAnswerRepo surveyRepo;

    public RegisterController(UserRepo userRepo, SurveyAnswerRepo surveyRepo) {
        this.userRepo = userRepo;
        this.surveyRepo = surveyRepo;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest req) {
        String username = req.username == null ? "" : req.username.trim();
        if (username.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username empty");
        }

        // ak už existuje, nech to neprepíše – radšej nech sa prihlási
        if (userRepo.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        UserEntity user = userRepo.save(new UserEntity(username));

        SurveyAnswerEntity a = new SurveyAnswerEntity(user);
        a.setVek(req.vek);
        a.setPohlavie(req.pohlavie);
        a.setVzdelanie(req.vzdelanie);
        a.setOdbor(req.odbor);
        a.setSocialneSiete(req.socialneSiete);
        a.setSkolenie(req.skolenie);
        a.setBezpecnost(req.bezpecnost);
        a.setPhishing(req.phishing);

        surveyRepo.save(a);
    }

    public static class RegisterRequest {
        public String username;

        public String vek;
        public String pohlavie;
        public String vzdelanie;
        public String odbor;
        public String socialneSiete;
        public String skolenie;
        public String bezpecnost;
        public String phishing;
    }
}