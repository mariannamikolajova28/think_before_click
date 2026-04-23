package sk.tuke.thinkbeforeclick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.tuke.thinkbeforeclick.entity.UserEntity;
import sk.tuke.thinkbeforeclick.repo.UserRepo;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PutMapping("/users/{username}")
    public void updateUsername(@PathVariable String username,
                               @RequestBody UpdateUsernameRequest req) {

        String newUsername = (req.newUsername == null) ? "" : req.newUsername.trim();

        if (newUsername.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty username");
        }

        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // ak už existuje používateľ s novým menom, nepovolíme zmenu
        if (userRepo.findByUsername(newUsername).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }

        user.setUsername(newUsername);
        userRepo.save(user);
    }

    public static class UpdateUsernameRequest {
        public String newUsername;
    }
}