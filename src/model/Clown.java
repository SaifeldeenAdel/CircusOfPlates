package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Clown extends ImageObject implements GameObject{
    private BufferedImage image;
    private Circus circus;

    public Clown(int x, int y, int width, int height, Circus circus){
        super(x,y,"/clown.png", width, height);
        this.circus = circus;
        System.out.println(getX() + " " + getY());

    }

    public Point getLeftStickPosition(){
        return new Point(this.getX() + 5,this.getY());
    }

    public Point getRightStickPosition(){
        return new Point(this.getX() + this.getWidth() - 5,this.getY());
    }

//    public void addToWorld(){
//        this.circus.getControlableObjects().add(this);
//    }

    @Override
    public void setY(int mY) {
        return;
    }
}
