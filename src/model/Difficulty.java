package model;

public enum Difficulty {
    EASY(2, 5), MEDIUM(3,3), HARD(5,2);
    private final int speed;
    private final int waveInterval;

    Difficulty(int speed, int waveInterval) {
        this.speed = speed;
        this.waveInterval = waveInterval;
    }

    public int getSpeed(){
        return speed;
    }

    public int getWaveInterval() {
        return waveInterval;
    }
}
