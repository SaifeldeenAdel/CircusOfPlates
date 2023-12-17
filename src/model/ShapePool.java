package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;
import java.lang.Math;

public class ShapePool {
    private int width;
    private int height;
    private long endTime;
    private Random random ;
    private Shape randomShape;
    private HashMap<Shape,Long> inUse;
    private HashMap<Shape,Long> queued;

    public ShapePool(int width, int height, long endtime){
        this.width= width;
        this.height = height;
        this.endTime = endTime;
        random = new Random();
        inUse = new HashMap<>();
        queued = new HashMap<>();
    }

    public synchronized Shape getQueuedShape(){
        long shapeTime = System.currentTimeMillis();
        if(!queued.isEmpty())
        {
            for(Map.Entry<Shape,Long> set : queued.entrySet()){
                if(set.getValue()>=endTime)
                {
                    queued.remove(set);
                }
                else{
                    int randValueX = random.nextInt(height,width);
                    int randValueY = random.nextInt(height,width);
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
        int randValueX = random.nextInt(height,width);
        int randValueY = random.nextInt(height,width);
        Shape newShape = ShapeFactory.getInstance().getRandomShape();
        newShape.setX(randValueX);
        newShape.setY(randValueY);
        return newShape;
    }
    public void queueShape(Shape shape, Long time){
        inUse.remove(shape);
        queued.put(shape,time);
    }
}
