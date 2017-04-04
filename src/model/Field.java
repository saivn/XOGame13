package model;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 21.03.17
 * Time: 13:02
 * To change this template use File | Settings | File Templates.
 */
public class Field {
    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 3;
    static final String NULL_CELL = "-1";
    public static ArrayList<TreeMap<String, String>> VisitingRules;

    public Field() {
        initVisitingRules();
    }

    public void initVisitingRules() {
        VisitingRules = new ArrayList<TreeMap<String, String>>();
        for (int i = 0; i < SIZE_X; i++) {
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            for (int j = 0; j < SIZE_Y; j++) {
                String key = String.valueOf(i) + String.valueOf(j);
                treeMap.put(key, NULL_CELL);
            }
            VisitingRules.add(treeMap); //1-3
        }

        for (int i = 0; i < SIZE_X; i++) {
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            for (int j = 0; j < SIZE_Y; j++) {
                String key = String.valueOf(j) + String.valueOf(i);
                treeMap.put(key, NULL_CELL);
            }
            VisitingRules.add(treeMap);    //4-6
        }

        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("02", NULL_CELL);
        treeMap.put("11", NULL_CELL);
        treeMap.put("20", NULL_CELL);
        VisitingRules.add(treeMap);        //7

        treeMap = new TreeMap<String, String>();
        treeMap.put("00", NULL_CELL);
        treeMap.put("11", NULL_CELL);
        treeMap.put("22", NULL_CELL);
        VisitingRules.add(treeMap);        //8
    }

    public static void showVisiting() {
        for (TreeMap<String, String> treeMap : Field.VisitingRules) { //7 shtuk
            for (Map.Entry entry : treeMap.entrySet()) {      //po 3 v kagdoy
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    public static void setPosition(String value, String key) {
        for (TreeMap<String, String> treeMap : Field.VisitingRules) {
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                }
            }
        }
    }

    public static boolean checkPositionEmpty(String key) {
        boolean flagCheck = true;
        for (TreeMap<String, String> treeMap : Field.VisitingRules) {
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getKey().equals(key)) {
                    if (!entry.getValue().equals(NULL_CELL)) {
                        return flagCheck = false;
                    }
                }
            }
        }
        return flagCheck;
    }

    public static int getPositionFullVictory(String value) {
        int pos = 0;
        for (TreeMap<String, String> treeMap : Field.VisitingRules) {
            int sum = 0;
            pos++;
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                if (entry.getValue().equals(value)) {
                    sum++;
                    if (sum == 3) {
                        String str = treeMap.firstKey();
                        return pos - 1;
                    }
                }
            }
        }
        return 0;
    }
}
