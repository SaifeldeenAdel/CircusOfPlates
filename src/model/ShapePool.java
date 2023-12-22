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
        this.deathTime = deathTime;
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
//                    int randValueX = random.nextInt(screenHeight, screenWidth);
//                    int randValueY = random.nextInt(screenHeight, screenWidth);
                    int randValueX = random.nextInt(50, screenWidth-50);
                    int randValueY = random.nextInt(-1*screenHeight, 0);
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
        int randValueX = random.nextInt(50, screenWidth-50);
        int randValueY = random.nextInt(-1*screenHeight, 0);
        return ShapeFactory.getInstance().getRandomShape(randValueX, randValueY);
    }
    public void queueShape(Shape shape){
        inUse.remove(shape);
        queued.put(shape,System.currentTimeMillis());
    }
}
