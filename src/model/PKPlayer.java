package model;

import view.FieldImage;
import view.VLine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
public class PKPlayer extends Thread {
    public static int count = 0;
    public static int flagVictory = 0;
    Field field;

    public PKPlayer(Field field) {
        this.field = field;
    }

    public synchronized void run() {
        int NumberMove = (Game.PervHod.equals("Human")) ? 4 : 5;
        flagVictory = 0;
        Move move = new Move();

        for (int i = 0; i < NumberMove; i++) {
            if (!checkVictoryMovePK(move)) {        //не выигрыш?
                setPositionPointRandom(move);       //ставим случайно
            } else {
                flagVictory = 1;                    //выигрыш
            }
            Point pos = move.getIndexPositionMove();
            System.out.println(pos.x + " y " + pos.y);
            move.setPositionView("PK", pos.x, pos.y);    //вывод хода

            if (flagVictory == 1) break;
            waitTread();                               //заснули, ждем хода соперника
        }
        if (flagVictory == 1) {
            count++;
            FieldImage.repaintCountPlayers("PK", count);    //счет
            VLine.setVictoryLine("PK");                    //линия победы
            FieldImage.setLabelStatusVictoryView("pk");    //надпись победы
            FieldImage.setMovingPicture("PK", 260);
        } else {
            System.out.println("draw");
            FieldImage.setLabelStatusVictoryView("draw");   //ничья
        }

    }

    synchronized void waitTread() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }

    public boolean checkVictoryMovePK(Move move) {
        int checkSumVictory = 0;
        int checkSumNull = 0;
        Integer xx, yy;

        for (TreeMap<String, String> treeMap : Field.VisitingRules) { //7 shtuk
            checkSumVictory = 0;
            checkSumNull = 0;
            for (Map.Entry obj : treeMap.entrySet()) {      //po 3 v kagdoy
                if (obj.getValue().equals("PK")) checkSumVictory++;
                if (obj.getValue().equals(Field.NULL_CELL)) checkSumNull++;
            }

            if ((checkSumVictory == Field.SIZE_X - 1) && (checkSumNull > 0)) {//if dva uge est
                for (Map.Entry entry : treeMap.entrySet()) { //to postavim v tretiy
                    if (entry.getValue().equals(Field.NULL_CELL)) {
                        String key = String.valueOf(entry.getKey());
                        yy = Integer.valueOf(key.substring(1, 2));
                        xx = Integer.valueOf(key.substring(0, 1));
                        entry.setValue("PK");                //"-1" -> "PK" setVisitingRules
                        move.setIndexPositionMove(xx, yy);
                    }
                }
                return true;
            }
        }
        return false;
    }


    public void setPositionPointRandom(Move move) {
        ArrayList<String> tempCells = new ArrayList<String>();  //temp for "-1"
        int countZero = 0;
        for (TreeMap<String, String> treeMap : Field.VisitingRules) {
            for (Map.Entry entry : treeMap.entrySet()) {
                if (entry.getValue().equals("-1")) {
                    tempCells.add((String) entry.getKey());
                    countZero++;
                }
            }
        }
        if (countZero > 0) {
            int random;
            random = new Random().nextInt(tempCells.size());
            String randomPosition = tempCells.get(random);
            int xx = Integer.valueOf(randomPosition.substring(0, 1));
            int yy = Integer.valueOf(randomPosition.substring(1, 2));
            move.setIndexPositionMove(xx, yy);

            Field.setPosition("PK", randomPosition);
        }
    }
}

