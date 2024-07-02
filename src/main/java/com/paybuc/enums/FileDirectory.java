/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paybuc.enums;

/**
 *
 * @author Brandon
 */
public enum FileDirectory {
    Lottory(1,"bin/lotto.csv"),
    LottoryPlusOne(2,"bin/lotto_plus_one.csv"),
    LottoryPlusTwo(3,"bin/lotto_plus_two.csv"),
    Powerball(4,"bin/powerball.csv"),
    PowerballPlus(5,"bin/powerball_plus.csv"),
    DailyLotto(5,"bin/sa_daily_lotto.csv")
    ;

    //Enum variables
    final int id;
    final String label;

    /** Creates a new instance of XMLEnum */
    FileDirectory(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return label;
    }

    public static FileDirectory fromString(final String str) {
        for (FileDirectory e : FileDirectory.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static FileDirectory fromId(final int id) {
        for (FileDirectory e : FileDirectory.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
    
}
