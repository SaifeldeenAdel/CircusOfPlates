package model;

public interface ScoreObservable {
    void addObserver(ScoreObserver observer);
    void notifyObservers();
}
