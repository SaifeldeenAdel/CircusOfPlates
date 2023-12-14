package view;

import eg.edu.alexu.csd.oop.game.GameEngine;
import model.Levels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JPanel mainMenu;
    private JButton newGameButton;
    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    private JButton exitButton;

    public MainMenu(){
        setContentPane(mainMenu);
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (easyButton.isSelected()){
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082", new Circus(Levels.EASY),3);
                } else if (mediumButton.isSelected()){
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082", new Circus(Levels.MEDIUM),3);
                }else if (hardButton.isSelected()){
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082", new Circus(Levels.HARD),3);
                }
            }
        });
    }
}
