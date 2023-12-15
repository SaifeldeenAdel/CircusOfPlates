package view;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import model.Clown;
import model.Levels;

import java.util.LinkedList;
import java.util.List;

public class Circus implements World {
    private List<GameObject> constantObjects = new LinkedList<GameObject>();
    private List<GameObject> movableObjects = new LinkedList<GameObject>();
    private List<GameObject> controllableObjects = new LinkedList<GameObject>();
    private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private long endTime = System.currentTimeMillis(), startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private Levels level;
    private Clown clown;

    private Circus(int screenWidth, int screenHeight, Levels level)  {
        this.level = level;
        this.width = screenWidth;
        this.height = screenHeight;
        initializeClown();
//        initializeBars();

    }

    public Circus(Levels level){
        this(900, 700, level);
    }

    // Initializes clown object and adds it to our World
    public void initializeClown(){
        int clownWidth = (int) Math.round(height * 0.35);
        int clownHeight = (int) Math.round(height * 0.37);
        clown = new Clown((int) Math.round(width / 2.0)-(clownWidth /2), height-clownHeight-15, clownWidth, clownHeight , this);
        this.getControlableObjects().add(clown);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constantObjects;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movableObjects;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controllableObjects;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        if(!timeout){
            endTime = System.currentTimeMillis();
        }
        return true;
    }

    @Override
    public String getStatus() {
        return "Time=" + Math.max(0, (MAX_TIME - (endTime-startTime))/1000);
    }

    @Override
    public int getSpeed() {
        return 5;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }
}
