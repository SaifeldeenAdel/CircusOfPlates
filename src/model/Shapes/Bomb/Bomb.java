package model.Shapes.Bomb;
import model.Shape;
import model.ShapeColor;
import model.ShapeState;

import java.awt.image.BufferedImage;

public class Bomb extends Shape {
    public Bomb(int posX, int posY, BufferedImage[] images, ShapeColor color) {
        super(posX, posY, images, color);
    }
}