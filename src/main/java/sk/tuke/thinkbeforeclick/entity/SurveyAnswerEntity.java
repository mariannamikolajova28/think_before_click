package sk.tuke.thinkbeforeclick.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "survey_answers")
public class SurveyAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(length = 20)
    private String vek;

    @Column(length = 30)
    private String pohlavie;

    @Column(length = 30)
    private String vzdelanie;

    @Column(length = 200)
    private String odbor;

    @Column(name = "socialne_siete", length = 20)
    private String socialneSiete;

    @Column(length = 10)
    private String skolenie;

    @Column(length = 20)
    private String bezpecnost;

    @Column(length = 20)
    private String phishing;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public SurveyAnswerEntity() {}

    public SurveyAnswerEntity(UserEntity user) {
        this.user = user;
    }

    public Long getId() { return id; }
    public UserEntity getUser() { return user; }

    public String getVek() { return vek; }
    public String getPohlavie() { return pohlavie; }
    public String getVzdelanie() { return vzdelanie; }
    public String getOdbor() { return odbor; }
    public String getSocialneSiete() { return socialneSiete; }
    public String getSkolenie() { return skolenie; }
    public String getBezpecnost() { return bezpecnost; }
    public String getPhishing() { return phishing; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setUser(UserEntity user) { this.user = user; }
    public void setVek(String vek) { this.vek = vek; }
    public void setPohlavie(String pohlavie) { this.pohlavie = pohlavie; }
    public void setVzdelanie(String vzdelanie) { this.vzdelanie = vzdelanie; }
    public void setOdbor(String odbor) { this.odbor = odbor; }
    public void setSocialneSiete(String socialneSiete) { this.socialneSiete = socialneSiete; }
    public void setSkolenie(String skolenie) { this.skolenie = skolenie; }
    public void setBezpecnost(String bezpecnost) { this.bezpecnost = bezpecnost; }
    public void setPhishing(String phishing) { this.phishing = phishing; }
}