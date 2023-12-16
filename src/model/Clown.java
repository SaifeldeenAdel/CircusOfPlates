package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Clown extends ImageObject implements GameObject{
    private Point leftStackCenter;
    private Point rightStackCenter;
    private BufferedImage image;
    private ScoreManager scoreManager;
    private int score;

    public Clown(int x, int y, int width, int height){
        super(x,y,"/clown.png", width, height);
        this.scoreManager = new ScoreManager();
        System.out.println(getX() + " " + getY());

    }

    public Point getLeftStackCenter(){
        return leftStackCenter;
    }

    public void setLeftStackCenter(Point leftStackCenter) {
        this.leftStackCenter = leftStackCenter;
    }

    public Point getRightStackCenter() {
        return rightStackCenter;
    }

    public void setRightStackCenter(Point rightStackCenter) {
        this.rightStackCenter = rightStackCenter;
    }

    public boolean intersectsStack(Shape s, Point stackCenter){
        // Shape did not pass stick
        if((s.getHeight() + s.getY()) > stackCenter.y) {
            return false;
        }
        // Y of shape is not close to Y of Left Center
        if(Math.abs((s.getY() + s.getHeight()) - stackCenter.y) > 5){ // REPLACE 5 WITH LEVEL???!!!!
            return false;
        }
        // X of stack center is not within x of shape
        if(Math.abs((s.getX() + (s.getWidth()/2)) - stackCenter.x) > s.getWidth()/4){
            return false;
        }
        return true;
    }

    public void addToStack(Shape s, Point stackCenter){
        s.setY(stackCenter.y - s.getHeight());
    }

    @Override
    public void setY(int mY) {
        return;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }


}
