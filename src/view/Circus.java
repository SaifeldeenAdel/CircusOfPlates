package view;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import model.Clown;

import java.util.LinkedList;
import java.util.List;

public class Circus implements World {
    private List<GameObject> constantObjects = new LinkedList<GameObject>();
    private List<GameObject> movableObjects = new LinkedList<GameObject>();
    private List<GameObject> controllableObjects = new LinkedList<GameObject>();
    private final int width;
    private final int height;
    private static Circus instance;
    private Clown clown;

    private Circus(int screenWidth, int screenHeight){
        width = screenWidth;
        height = screenHeight;
        System.out.println((int) Math.round(height * 0.53));
//        initializeClown();
//        initializeBars();
        clown = new Clown((int) Math.round(width / 2.0)-((int) (Math.round(width * 0.25) /2)), height-(int) Math.round(height * 0.47), (int) Math.round(width * 0.25), (int) Math.round(height * 0.45), this);
        this.getControlableObjects().add(clown);

    }

    public static Circus getInstance(){
        if(instance == null){
            return new Circus(900, 700);
        }
        return instance;
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
        return false;
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public int getControlSpeed() {
        return 0;
    }
}
