import eg.edu.alexu.csd.oop.game.GameEngine;
import view.Circus;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
//        JMenuBar menuBar = new JMenuBar();
//
//        JMenu menu = new JMenu("File");
//        JMenuItem newMenuItem = new JMenuItem("New Game");
//        JMenuItem pauseMenuItem = new JMenuItem("Pause");
//        JMenuItem resumeMenuItem = new JMenuItem("Resume");
//        menu.add(newMenuItem);
//        menu.addSeparator();
//        menu.add(pauseMenuItem);
//        menu.add(resumeMenuItem);
//        menuBar.add(menu);

        final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082", Circus.getInstance(), Color.WHITE);

    }
}