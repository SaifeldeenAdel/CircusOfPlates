package model;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;

public abstract class Shape extends ImageObject  implements GameObject {
    private ShapeState state;
    private ShapeColor color;

    public Shape(int posX, int posY, BufferedImage[] images, ShapeState state, ShapeColor color){
        super(posX, posY, images);
        this.state = state;
        this.color = color;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[0];
    }

    public ShapeState getState() {
        return state;
    }

    public ShapeColor getColor() {
        return color;
    }

    public void setState(ShapeState state) {
        this.state = state;
    }
}
