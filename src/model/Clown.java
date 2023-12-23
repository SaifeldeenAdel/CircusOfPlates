package model;

import eg.edu.alexu.csd.oop.game.GameObject;
import view.Circus;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.List;

public class Clown extends ImageObject implements GameObject{
    private Point leftStackCenter;
    private Point rightStackCenter;
    private ScoreManager scoreManager;
    private Stack<Shape> leftStack;
    private Stack<Shape> rightStack;


    public Clown(int x, int y, int width, int height){
        super(x,y,"/clown1.png", width, height);
        this.scoreManager = new ScoreManager();
        System.out.println(getX() + " " + getY());
        leftStackCenter = new Point(x+5, y);
        rightStackCenter = new Point(x+width-5, y);
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public void refreshStackCenters(){
        if(getLeftStack().isEmpty()){
            setLeftStackCenter(new Point(getX() +5, getY()));
        } else {
            Shape top = getLeftStack().peek();
            setLeftStackCenter(new Point(top.getX() + top.getWidth()/2, top.getY()));
        }
        if(getRightStack().isEmpty()){
            setRightStackCenter(new Point(getX()+getWidth()-5, getY()));
        } else {
            Shape top = getRightStack().peek();
            setRightStackCenter(new Point(top.getX() + top.getWidth()/2, top.getY()));
        }
    }

    public Stack<Shape> getLeftStack() {
        return leftStack;
    }

    public Stack<Shape> getRightStack() {
        return rightStack;
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
        if(Math.abs((s.getY() + s.getHeight()) - stackCenter.y) > 5){ // REPLACE 5 WITH LEVEL???!!!! //probably needs to be modified
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

    public boolean shouldScore(Shape s, Stack<Shape> stack){
        if (stack.size()>=2){
            List<Shape> lastTwo = stack.subList(stack.size()-2,stack.size());
            ShapeColor color = s.getColor();
//            System.out.println(color);
            for(Shape shape: lastTwo){
                if(shape.getColor() != color){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public void addToStack(Shape s, Stack<Shape> stack){
        if(shouldScore(s, stack)){
            s.setVisible(false);
            removeFromStack(stack);
            refreshStackCenters();
        } else {
            stack.push(s);
        }
    }

    public void removeFromStack(Stack<Shape> stack) { //put them in object pool maybe?
        for(int i =0; i<2; i++){
            Shape s = stack.pop();
            s.setVisible(false);
//            pool.queueShape(s);
        }
        scoreManager.notifyObservers();
    }

    @Override
    public void setY(int mY) {
        return;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public boolean bombGameOver(Shape s){
         if(s.getColor() == ShapeColor.BLACK)
            return true;
         return false;
    }
}
