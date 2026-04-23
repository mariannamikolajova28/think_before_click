package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    private final UserRepo userRepo;

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody LoginRequest req) {
        String username = req.username == null ? "" : req.username.trim();
        if (username.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username empty");
        }

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User does not exist"
                ));
    }

    public static class LoginRequest {
        public String username;
    }
}