package model;

public class Falling implements ShapeState{
    @Override
    public void move(Shape s, Difficulty d) {
        s.setY(s.getY() + d.getSpeed());
    }
}
