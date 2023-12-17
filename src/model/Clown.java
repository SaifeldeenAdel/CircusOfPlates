package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Clown extends ImageObject implements GameObject{
    private Point leftStackCenter;
    private Point rightStackCenter;
    private BufferedImage image;
    private ScoreManager scoreManager;
    private int score;

    private Stack<Shape> leftStack;
    private Stack<Shape> rightStack;

    public Clown(int x, int y, int width, int height){
        super(x,y,"/clown.png", width, height);
        this.scoreManager = new ScoreManager();
        System.out.println(getX() + " " + getY());

        leftStack = new Stack<>();
        rightStack = new Stack<>();


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

    public void addToStackCenter(Shape s, Point stackCenter){
        s.setY(stackCenter.y - s.getHeight());
    }

    public void addToStack(Shape s, ClownStack stack){
        if(stack == ClownStack.LEFT)
            leftStack.push(s);
        else
            rightStack.push(s);
    }

    public void removeFromStack(ClownStack stack) { //put them in object pool maybe?
        if(stack == ClownStack.LEFT) {
            leftStack.pop();
            leftStack.pop();
            leftStack.pop();
        }
        else {
            rightStack.pop();
            rightStack.pop();
            rightStack.pop();
        }
        this.score++;
    }

    @Override
    public void setY(int mY) {
        return;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }


}
