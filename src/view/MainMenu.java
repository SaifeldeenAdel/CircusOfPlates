package view;

import eg.edu.alexu.csd.oop.game.GameEngine;
import model.Difficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082 7978", new Circus(Difficulty.EASY),2);
                } else if (mediumButton.isSelected()){
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082 7978", new Circus(Difficulty.MEDIUM),2);
                }else if (hardButton.isSelected()){
                    final GameEngine.GameController gameController = GameEngine.start("8139 8277 8082 7978", new Circus(Difficulty.HARD),2);
                }
            }
        });
    }
}