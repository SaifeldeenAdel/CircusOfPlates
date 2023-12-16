package model;

public class ScoreListener implements ScoreObserver{
    private int score = 0;
    private int max;
    private boolean win = false;
    public ScoreListener(int maxScore) {
        max = maxScore;
    }

    @Override
    public void updateScore() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
