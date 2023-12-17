package view;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import model.Clown;
import model.Levels;
import model.ScoreListener;
import model.Shape;

import java.util.LinkedList;
import java.util.List;

public class Circus implements World {
    private List<GameObject> constantObjects = new LinkedList<>();
    private List<GameObject> movableObjects = new LinkedList<GameObject>();
    private List<GameObject> controllableObjects = new LinkedList<GameObject>();
    private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private static int MAX_SCORE = 5;	// 1 minute
    private long endTime = System.currentTimeMillis(), startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private Levels level;
    private Clown clown;
    private ScoreListener score;

    private Circus(int screenWidth, int screenHeight, Levels level)  {
        this.level = level;
        this.width = screenWidth;
        this.height = screenHeight;
        this.score = new ScoreListener(5);
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
        clown = new Clown((int) Math.round(width / 2.0)-(clownWidth /2), height-clownHeight-15, clownWidth, clownHeight);
        clown.getScoreManager().addObserver(score);
        this.getControlableObjects().add(clown);
    }

    public boolean outOfWorld(Shape s){
        return s.getY() > this.getHeight();
    }

    public Levels getLevel() {
        return level;
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
        boolean timeout = score.getScore() == MAX_SCORE || System.currentTimeMillis() - startTime > MAX_TIME;
        if(!timeout){
            endTime = System.currentTimeMillis();

            for(GameObject moving: movableObjects){
                Shape shape = (Shape) moving;
                shape.getState().move(shape);
                if(clown.intersectsStack(shape, clown.getLeftStackCenter())){
                    clown.addToStackCenter(shape, clown.getLeftStackCenter());
                    clown.addToLeftStack(shape);
                    getMovableObjects().remove(shape);
                    getControlableObjects().add(shape);
                    //check last 3 shapes' colors

                }else if(clown.intersectsStack(shape, clown.getRightStackCenter())){
                    clown.addToStackCenter(shape, clown.getRightStackCenter());
                    clown.addToRightStack(shape);
                    getMovableObjects().remove(shape);
                    getControlableObjects().add(shape);
                    //check last 3 shapes' colors

                } else if(outOfWorld(shape)){
//                pool.queueShape(shape)
                }
            }
        }

        return !timeout;
    }

    @Override
    public String getStatus() {
        return "Score: " + score.getScore() + "   |    " + "Time: " + Math.max(0, (MAX_TIME - (endTime-startTime))/1000);
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
