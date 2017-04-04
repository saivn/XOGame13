package control;

import model.*;
import view.FieldImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 20.03.17
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */

public class HController extends MouseAdapter {
    String[] pos = {"00", "01", "02",
            "10", "11", "12",
            "20", "21", "22"};
    public static int flagVictory = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        int xMouse = e.getX();                                 //0..SIZE_GAME_POLE_X
        int yMouse = e.getY();                                 //0..SIZE_GAME_POLE_Y
        int xCell = xMouse * 3 / FieldImage.SIZE_GAME_POLE_X;   //0,1,2
        int yCell = yMouse * 3 / FieldImage.SIZE_GAME_POLE_Y;   //0,1,2
        String pos = Integer.toString(xCell) + Integer.toString(yCell);

        System.out.println("flagVictory " + HumanPlayer.flagVictory + " PK flv " + PKPlayer.flagVictory);
        System.out.println("check " + Field.checkPositionEmpty(pos));

        if (Field.checkPositionEmpty(pos) &&
                (HumanPlayer.flagVictory == 0) && (PKPlayer.flagVictory == 0)
                ) {//защита чтоб не показывал
            Move move = new Move();
            move.setPositionView("Human", xCell, yCell); //на экран

            Field.setPosition("Human", pos);             //маркируем visitRules

            if (checkVictoryMoveHM()) {                  //pobeda?
                flagVictory = 1;
            }
            notifyHumanAndPK();                          //будим потоки
        }
    }

    public synchronized void notifyHumanAndPK() {
        if (flagVictory == 0) {                   //когда победа Human тогда PK не будим
            synchronized (Game.pkPlayer) {
                Game.pkPlayer.notify();           //пробуждаем поток
            }
        }

        synchronized (Game.humanPlayer) {
            Game.humanPlayer.notify();
        }
    }

    public boolean checkVictoryMoveHM() {
        int checkSumVictory;
        for (TreeMap<String, String> treeMap : Field.VisitingRules) { //7 shtuk
            checkSumVictory = 0;
            for (Map.Entry obj : treeMap.entrySet()) {      //po 3 v kagdoy
                if (obj.getValue().equals("Human")) checkSumVictory++;
            }

            if (checkSumVictory == Field.SIZE_X) {//if dva uge est
                return true;
            }
        }
        return false;
    }

}