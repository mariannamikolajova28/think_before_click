package sk.tuke.thinkbeforeclick;

public class Score {

    private String name;
    private int successPercent; // úspešnosť v %
    private int attempts;       // pokusy

    public Score() {}

    public Score(String name, int successPercent, int attempts) {
        this.name = name;
        this.successPercent = successPercent;
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public int getSuccessPercent() {
        return successPercent;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuccessPercent(int successPercent) {
        this.successPercent = successPercent;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}