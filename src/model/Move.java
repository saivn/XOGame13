package model;

import view.FieldImage;
import view.MovesView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class Move extends Thread {
    private Point indexPositionMove;
    public static Image imageMove;


    public void setPositionView(String player, int xCell, int yCell) {

        String path = (player.equals("Human")) ? "view/img/nolik.jpg" :
                "view/img/krest.jpg";

        int x = (FieldImage.SIZE_GAME_POLE_X / 3) * xCell;
        int y = (FieldImage.SIZE_GAME_POLE_Y / 3) * yCell;

        MovesView movesView = new MovesView(new ImageIcon(
                ClassLoader.getSystemResource(path)).getImage(), x + 2, y + 5);
        movesView.setBounds(x + 2, y + 5, 45, 45);
        FieldImage.gamePanel.add(movesView);
        FieldImage.gamePanel.repaint();
    }

    public Point getIndexPositionMove() {
        return indexPositionMove;
    }

    public void setIndexPositionMove(int positionX, int positionY) {
        Point point = new Point(positionX, positionY);
        indexPositionMove = point;
    }
}