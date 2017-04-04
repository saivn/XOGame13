package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 20.03.17
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class JVImagePanel extends JPanel {
    private Image img;
    public int x;
    public int y;
    public JVImagePanel(Image img){//}, int x, int y) {
        this.img = img;
        Dimension size = new Dimension(
                img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        setOpaque(false);

    }
    public void paintComponent(Graphics g) {
        
        g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null),  null);
    }

}
