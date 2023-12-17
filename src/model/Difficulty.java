package model;

public enum Difficulty {
    EASY(2), MEDIUM(3), HARD(5);
    private final int speed;

    Difficulty(int speed) {
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
}
