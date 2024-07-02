package com.paybuc.pojos;

public class DailyLottoDto extends DrawObject{
    private int one;
    private int two;
    private int three;
    private int four;
    private int bonus;

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getThree() {
        return three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String toString(){
        return new String(getOne()+" "+getTwo()+" "+getThree()+" "+getFour()+" "+getBonus());
    }
}