package model.Shapes.Bowl;
import model.Shape;
import model.ShapeColor;
import model.ShapeState;

import java.awt.image.BufferedImage;

public class Bowl extends Shape {
    public Bowl(int posX, int posY, BufferedImage[] images, ShapeState state, ShapeColor color) {
        super(posX, posY, images, state, color);
    }
}
