package model;

import java.util.Random;

public class ShapeFactory extends Shape{
    private static ShapeFactory shapeFactory = null;
    private Random random;
    private ShapeFactory() {
        random = new Random();
    }
    public synchronized static ShapeFactory getInstance(){
        if(shapeFactory==null){
            shapeFactory = new ShapeFactory();
        }
        return shapeFactory;
    }
    public Shape getRandomShape(){
       return null;
    }

}
