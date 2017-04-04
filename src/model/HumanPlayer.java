package model;

import control.HController;
import view.FieldImage;
import view.VLine;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 13:49
 * To change this template use File | Settings | File Templates.
 */

public class HumanPlayer extends Thread {
    public static int count = 0;
    public static int flagVictory = 0;
    private int indexPositionMove;
    Field field;

    public HumanPlayer(Field field) {
        this.field = field;
    }

    public synchronized void run() {
        int NumberMove = (Game.PervHod.equals("Human")) ? 5 : 4;
        flagVictory = 0;

        for (int i = 0; i < NumberMove; i++) {
            waitThread();                                   //заснул, разбудит pk
            if (HController.flagVictory == 1) {
                flagVictory = 1;
                HController.flagVictory = 0;
                break;
            }
        }

        if (flagVictory == 1) {
            count++;
            System.out.println("pobeda hum");

            FieldImage.repaintCountPlayers("Human", count);    //счет
            VLine.setVictoryLine("Human");                    //линия победы
            FieldImage.setLabelStatusVictoryView("human");    //надпись победы
            FieldImage.setMovingPicture("Human", 20);

        } else {
            System.out.println("draw");
            FieldImage.setLabelStatusVictoryView("draw");
        }
    }

    synchronized void waitThread() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public int getIndexPositionMove() {
        return indexPositionMove;
    }

    public void setIndexPositionMove(int indexPositionMove) {
        this.indexPositionMove = indexPositionMove;
    }
}


