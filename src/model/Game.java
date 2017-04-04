package model;

import view.FieldImage;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 20.03.17
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class Game extends JFrame {
    public static String PervHod = "PK";
    public static HumanPlayer humanPlayer;
    public static PKPlayer pkPlayer;
    public static JFrame jFrame;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                jFrame = new JFrame("XOGame");
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jFrame.setLocation(440, 400);
                jFrame.setSize(370, 280);
                jFrame.setResizable(false);

                ActivationGame();
            }
        });
    }

    public static void ActivationGame() {
        FieldImage fieldImage = new FieldImage();
        Field field = new Field();
        pkPlayer = new PKPlayer(field);
        humanPlayer = new HumanPlayer(field);

        new Thread(humanPlayer).start();
        new Thread(pkPlayer).start();
    }
}
