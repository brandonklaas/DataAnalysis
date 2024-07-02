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
public enum FileLinks {
    Lottory(1,"https://www.africanlottery.net/download/sa_lotto.csv"),
    LottoryPlusOne(2,"https://www.africanlottery.net/download/sa_lotto_plus_1.csv"),
    LottoryPlusTwo(3,"https://www.africanlottery.net/download/sa_lotto_plus_2.csv"),
    Powerball(4,"https://www.africanlottery.net/download/sa_powerball.csv"),
    PowerballPlus(5,"https://www.africanlottery.net/download/sa_powerball_plus.csv"),
    DailyLotto(5,"https://www.africanlottery.net/download/sa_daily_lotto.csv")
    ;
     
    //Enum variables
    final int id;
    final String label;

    /** Creates a new instance of XMLEnum */
    FileLinks(final int id, final String label) {
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

    public static FileLinks fromString(final String str) {
        for (FileLinks e : FileLinks.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static FileLinks fromId(final int id) {
        for (FileLinks e : FileLinks.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
    
}
