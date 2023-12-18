package model.Shapes.Plate;
import model.Shape;
import model.ShapeColor;
import model.ShapeState;

import java.awt.image.BufferedImage;

public class Plate extends Shape {

    public Plate(int posX, int posY, BufferedImage[] images, ShapeColor color) {
        super(posX, posY, images, color);
    }
}
