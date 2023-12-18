package model;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;

public abstract class Shape extends ImageObject  implements GameObject {
    private ShapeState state;
    private ShapeColor color;

    public Shape(int posX, int posY, BufferedImage[] images, ShapeColor color){
        super(posX, posY, images);
        this.state = new Falling();
        this.color = color;
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
