package sk.tuke.thinkbeforeclick.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scores")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable = false)
    private int successPercent;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public ScoreEntity() {}

    public ScoreEntity(UserEntity user, int successPercent) {
        this.user = user;
        this.successPercent = successPercent;
    }

    public Long getId() { return id; }
    public UserEntity getUser() { return user; }
    public int getSuccessPercent() { return successPercent; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setUser(UserEntity user) { this.user = user; }
    public void setSuccessPercent(int successPercent) { this.successPercent = successPercent; }
}