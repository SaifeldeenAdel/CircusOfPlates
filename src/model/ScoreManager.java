package model;

import java.util.ArrayList;

public class ScoreManager implements ScoreObservable{
    private ArrayList<ScoreObserver> observers = new ArrayList<>();
    @Override
    public void addObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(ScoreObserver s: observers){
            s.updateScore();
        }
    }
}
