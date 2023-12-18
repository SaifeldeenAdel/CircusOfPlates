package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapePool {
    private int screenWidth;
    private int screenHeight;
    private long deathTime;
    private Random random ;
    private Shape randomShape;
    private HashMap<Shape,Long> inUse;
    private HashMap<Shape,Long> queued;

    public ShapePool(int screenWidth, int screenHeight, long deathTime){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.deathTime = this.deathTime;
        random = new Random();
        inUse = new HashMap<>();
        queued = new HashMap<>();
    }

    public synchronized Shape getQueuedShape(){
        long shapeTime = System.currentTimeMillis();
        if(!queued.isEmpty())
        {
            for(Map.Entry<Shape,Long> set : queued.entrySet()){
                if(set.getValue()>= deathTime)
                {
                    queued.remove(set);
                }
                else{
                    int randValueX = random.nextInt(screenHeight, screenWidth);
                    int randValueY = random.nextInt(screenHeight, screenWidth);
                    Shape shape = set.getKey();
                    shape.setX(randValueX);
                    shape.setY(randValueY);
                    queued.remove(set);
                    inUse.put(shape,shapeTime);
                    return shape;
                }
            }
        }
        randomShape = setNewShapeObject();
        inUse.put(randomShape,shapeTime);
        return randomShape;
    }
    public Shape setNewShapeObject(){
        int randValueX = random.nextInt(screenHeight, screenWidth);
        int randValueY = random.nextInt(screenHeight, screenWidth);
        Shape newShape = ShapeFactory.getInstance().getRandomShape();
        newShape.setX(randValueX);
        newShape.setY(randValueY);
        return newShape;
    }
    public void queueShape(Shape shape){
        inUse.remove(shape);
        queued.put(shape,System.currentTimeMillis());
    }
}
