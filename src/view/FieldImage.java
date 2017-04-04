package view;
/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 20.03.17
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */

import control.HController;
import model.Game;
import model.HumanPlayer;
import model.PKPlayer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 12.05.16
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public class FieldImage extends JFrame {
    public static final int SIZE_GAME_POLE_X = 120;
    public static final int SIZE_GAME_POLE_Y = 120;
    public static JPanel panel0;
    public static JPanel gamePanel;

    public static JLabel jLabelCountPK;
    public static JLabel jLabelCountHM;
    public static JLabel jLabelPicturePK;
    public static JLabel jLabelPictureHM;
    public static JLabel jLabelStatusVictory = new JLabel();

    public FieldImage() {
        panel0 = new JPanel();
        panel0.setLayout(null);
        String pathFile = "view/img/kletk_pole.jpg";

        panel0 = new JVImagePanel(new ImageIcon(
                ClassLoader.getSystemResource(pathFile)).getImage());

        panel0.add(setGamePole());
        panel0.add(setJButtonRestart());

        jLabelCountPK = new JLabel();
        jLabelCountHM = new JLabel();

        panel0.add(setCountPlayers(jLabelCountHM, 40, HumanPlayer.count));
        panel0.add(setCountPlayers(jLabelCountPK, 280, PKPlayer.count));

        jLabelPicturePK = new JLabel();
        jLabelPictureHM = new JLabel();

        panel0.add(setPicturePlayers(jLabelPictureHM, "human", 20));
        panel0.add(setPicturePlayers(jLabelPicturePK, "pk", 260));

        Game.jFrame.setContentPane(panel0);
    }


    public static JButton setJButtonRestart() {
        JButton jButton = new JButton("Играть снова");
        jButton.setVisible(true);
        jButton.setBounds(100, 210, 160, 20);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Game.ActivationGame();
            }
        });

        return jButton;
    }

    public static JPanel setGamePole() {
        gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.addMouseListener(new HController());
        gamePanel.setBounds(120, 40, SIZE_GAME_POLE_X, SIZE_GAME_POLE_Y);
        return gamePanel;
    }

    public JLabel setCountPlayers(JLabel jLabel, int shift, int count) {
        String pathFile = "view/img/" + count + ".gif";
        URL url = ClassLoader.getSystemClassLoader().getResource(pathFile);
        Icon icon = new ImageIcon(url);

        jLabel.setIcon(icon);
        jLabel.setBounds(5 + shift, 40, 32, 55);
        return jLabel;
    }

    public static void repaintCountPlayers(String typePlayer, int count) {
        JLabel jLabel = (typePlayer.equals("PK")) ? jLabelCountPK : jLabelCountHM;

        if (count < 10) {
            // String str = "src/view/img/" + count + ".jpg";
            //  jLabel.setIcon(new ImageIcon(str));
        } else {
            HumanPlayer.count = 0;
            PKPlayer.count = 0;
        }
    }

    public static JLabel setPicturePlayers(JLabel jLabel,
                                           String typePlayer, int shift) {
        String pathFile = "view/img/" + typePlayer + "Anime0.gif";
        URL url = ClassLoader.getSystemClassLoader().getResource(pathFile);
        Icon icon = new ImageIcon(url);

        jLabel.setIcon(icon);
        jLabel.setBounds(5 + shift, 100, 100, 100);

        return jLabel;
    }


    public static void setLabelStatusVictoryView(String type) {
        String pathFile = (!type.equals("draw")) ? "view/img/" + type + "v.gif" :
                "view/img/" + type + ".gif";

        URL url = ClassLoader.getSystemClassLoader().getResource(pathFile);
        Icon icon = new ImageIcon(url);

        jLabelStatusVictory.setIcon(icon);
        jLabelStatusVictory.setBounds(120, 0, 140, 30);
        panel0.add(jLabelStatusVictory);
        panel0.repaint();
    }

    public static void setMovingPicture(String type, int shift) {
        int delay = 200;

        MovingPlayers movingPlayers = (type.equals("Human")) ?
                new MovingPlayers("Human", 200, 20) :
                new MovingPlayers("PK", 200, 20);

        new Thread(movingPlayers).start();

        if (type.equals("Human")) {
            jLabelPictureHM.add(movingPlayers);
        } else {
            jLabelPicturePK.add(movingPlayers);
        }

    }
}

