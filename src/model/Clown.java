package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;

import java.awt.image.BufferedImage;

public class Clown extends ImageObject implements GameObject{
    private BufferedImage image;
    private Circus circus;

    public Clown(int x, int y, int width, int height, Circus circus){
        super(x,y,"/clown.png", width, height);
        this.circus = circus;

    }

//    public void addToWorld(){
//        this.circus.getControlableObjects().add(this);
//    }

}
