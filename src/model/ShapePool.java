package model;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ShapePool {
    private int width;
    private int height;
    private long endTime;
    private Shape randomShape;
    private ConcurrentLinkedQueue<Shape> inUse;
    private ConcurrentLinkedQueue<Shape> queued;

    public ShapePool(int width, int height){
        this.width= width;
        this.height = height;
        inUse = new ConcurrentLinkedQueue<>();
        queued = new ConcurrentLinkedQueue<>();
    }

    public synchronized Shape getQueuedShape(){
        if(!inUse.isEmpty()){
            //iterate over queue
            //use object's geY to check if == height (?)
            // store in queued
        }
        setNewShapeObject();
        return randomShape;
    }
    public void setNewShapeObject(){
        //
    }
    public void queueShape(){

    }
}
