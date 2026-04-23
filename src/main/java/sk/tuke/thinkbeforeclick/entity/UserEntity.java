package sk.tuke.thinkbeforeclick.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String username;

    @Column(nullable = false)
    private int gamesPlayed = 0; // pokusy

    public UserEntity() {}

    public UserEntity(String username) {
        this.username = username;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public int getGamesPlayed() { return gamesPlayed; }

    public void setUsername(String username) { this.username = username; }

    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    public void incrementGamesPlayed() { this.gamesPlayed++; }
}