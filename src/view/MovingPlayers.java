package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 31.03.17
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class MovingPlayers extends JComponent implements ActionListener, Runnable{
    Image[] image = new Image[6];
    public Timer timer;
    public int numbPict =0;
    public int shift;
    public int numbJamp=0;

    public MovingPlayers(String typePlayer, int delay, int shift) {
        this.shift = shift;
        for (int i = 0; i < 5; i++) {
            String pathFile = (typePlayer.equals("Human"))? "view/img/humanAnime"+i+".gif":
                    "view/img/pkAnime"+i+".gif";
            image[i] = new ImageIcon(ClassLoader.getSystemResource(pathFile)).getImage();
        }
        timer = new Timer(delay, this);


    }
    @Override
    public void actionPerformed(ActionEvent arg0) {  //щелкает по таймеру
        setBounds(0,0,100,100);

        if (numbPict==5) numbPict=0;
        if (numbJamp<5)
        {  repaint();
           numbPict++;
           numbJamp++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image[numbPict], 0, 0, 80, 100,  null);
    }

    @Override
    public void run() { //по рунаблю стартуем
        //To change body of implemented methods use File | Settings | File Templates.
        timer.start();
    }
}


