package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 30.03.17
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class MovesView extends JPanel{
        private Image img;
        public int x;
        public int y;
        public MovesView(Image img, int x, int y) {
            this.img = img;
            this.x = x;
            this.y = y;

        }
        @Override
        public void paint(Graphics g) {
            g.drawImage(img, 0,0,null);
        }
    }

