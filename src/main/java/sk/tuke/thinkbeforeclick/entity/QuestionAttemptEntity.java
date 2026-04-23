package sk.tuke.thinkbeforeclick.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question_attempts")
public class QuestionAttemptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false, length = 255)
    private String imagePath; // napr. "questions/P01/xxx.png" alebo len "P01_03.png"

    @Column(nullable = false, length = 10)
    private String userAnswer; // napr. "P" alebo "R"

    @Column(nullable = false)
    private boolean correct;

    @Column(nullable = false, length = 50)
    private String gameSessionId; // UUID pre jednu hru

    @Column(nullable = false)
    private LocalDateTime answeredAt = LocalDateTime.now();

    public QuestionAttemptEntity() {}

    public QuestionAttemptEntity(UserEntity user, String imagePath, String userAnswer, boolean correct, String gameSessionId) {
        this.user = user;
        this.imagePath = imagePath;
        this.userAnswer = userAnswer;
        this.correct = correct;
        this.gameSessionId = gameSessionId;
    }

    public Long getId() { return id; }
    public UserEntity getUser() { return user; }
    public String getImagePath() { return imagePath; }
    public String getUserAnswer() { return userAnswer; }
    public boolean isCorrect() { return correct; }
    public String getGameSessionId() { return gameSessionId; }
    public LocalDateTime getAnsweredAt() { return answeredAt; }

    public void setUser(UserEntity user) { this.user = user; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public void setGameSessionId(String gameSessionId) { this.gameSessionId = gameSessionId; }
}