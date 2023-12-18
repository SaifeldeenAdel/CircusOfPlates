package model.Shapes.Box;
import model.Shape;
import model.ShapeColor;
import model.ShapeState;

import java.awt.image.BufferedImage;

public class Box extends Shape {
    public Box(int posX, int posY, BufferedImage[] images, ShapeColor color) {
        super(posX, posY, images, color);
    }
}
