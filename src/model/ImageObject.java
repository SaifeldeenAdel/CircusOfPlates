package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ImageObject implements GameObject{
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean visible;

    public ImageObject(int posX, int posY, String path, int width, int height){
        this.x = posX;
        this.y = posY;
        this.width = width;
        this.height= height;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = createScaledImage(ImageIO.read(getClass().getResourceAsStream(path)), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageObject(int posX, int posY, BufferedImage[] images){
        this.x = posX;
        this.y = posY;
        width = images[0].getWidth();
        height = images[0].getHeight();
        this.visible = true;
    }

    private BufferedImage createScaledImage(Image image, int scaledWidth, int scaledHeight){
        BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = scaled.createGraphics();
        g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaled;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth(){
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

}
