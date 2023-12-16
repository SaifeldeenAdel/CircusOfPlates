package model;

public enum Levels {
    EASY(2), MEDIUM(3), HARD(5);
    private final int speed;

    Levels(int speed) {
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }
}
