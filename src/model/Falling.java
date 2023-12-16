package model;

public class Falling implements ShapeState{
    @Override
    public void move(Shape s) {
        s.setY(s.getY() + 2);
    }
}
