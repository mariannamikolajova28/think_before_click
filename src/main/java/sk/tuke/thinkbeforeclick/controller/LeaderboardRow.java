package sk.tuke.thinkbeforeclick.controller;

public class LeaderboardRow {
    private String name;
    private int successPercent;
    private int attempts;

    public LeaderboardRow(String name, int successPercent, int attempts) {
        this.name = name;
        this.successPercent = successPercent;
        this.attempts = attempts;
    }

    public String getName() { return name; }
    public int getSuccessPercent() { return successPercent; }
    public int getAttempts() { return attempts; }
}