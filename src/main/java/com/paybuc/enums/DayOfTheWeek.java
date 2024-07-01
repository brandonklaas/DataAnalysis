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
public enum DayOfTheWeek {
    Sunday(1,"Sunday"),
    Monday(2,"Monday"),
    Tuesday(3,"Tuesday"),
    Wednesday(4,"Wednesday"),
    Thurday(5,"Thursday"),
    Friday(6,"Friday"),
    Saturday(7,"Saturday")
    ;
     
    //Enum variables
    final int id;
    final String label;

    /** Creates a new instance of XMLEnum */
    DayOfTheWeek(final int id, final String label) {
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

    public static DayOfTheWeek fromString(final String str) {
        for (DayOfTheWeek e : DayOfTheWeek.values()) {
            if (e.toString().equalsIgnoreCase(str)) {
                return e;
            }
        }
        return null;
    }

    public static DayOfTheWeek fromId(final int id) {
        for (DayOfTheWeek e : DayOfTheWeek.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
    
}
