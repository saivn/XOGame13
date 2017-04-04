package view;

import model.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 23.03.17
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class VLine extends JPanel {
    int x;
    int y;
    int width;
    int height;

    public static final int maxx= FieldImage.SIZE_GAME_POLE_X;
    public static int coordLineVictory[][] = {
            {maxx/6, 0, maxx/6, maxx},              //vertikal
            {maxx/6 + 2*maxx/6, 0, maxx/6 + 2*maxx/6, maxx},
            {maxx/6 + 4*maxx/6, 0, maxx/6 + 4*maxx/6, maxx},
            {0, maxx/6, maxx, maxx/6},              //gorizont
            {0, maxx/6 + 2*maxx/6, maxx, maxx/6 + 2*maxx/6},
            {0, maxx/6 + 4*maxx/6, maxx, maxx/6 + 4*maxx/6},
            {0, maxx, maxx, 0},
            {0, 0, maxx, maxx}
    };


    public VLine(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(2.0f));  // толщина равна 10
        g2.drawLine(x, y, width, height);
        repaint();
    }

    public static void setVictoryLine(String player) {
        int i = Field.getPositionFullVictory(player);

       // System.out.println("viborka " + i);
        VLine vLine = new VLine(coordLineVictory[i][0],
                coordLineVictory[i][1],
                coordLineVictory[i][2],
                coordLineVictory[i][3]);

        vLine.setBounds(0, 0, maxx, FieldImage.SIZE_GAME_POLE_Y);
        FieldImage.gamePanel.add(vLine);
        FieldImage.gamePanel.repaint();

    }
}
