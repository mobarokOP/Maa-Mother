package com.mobarok.pragnancytracker.database;

public class WeekModel {

    String ivLink;
    int sound;
    String tv1, tv2, tv3, tv4, tv5;

    public WeekModel(String ivLink, int sound, String tv1, String tv2, String tv3, String tv4, String tv5) {
        this.ivLink = ivLink;
        this.sound = sound;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
    }

    public String getTv1() {
        return tv1;
    }

    public String getTv2() {
        return tv2;
    }

    public String getTv3() {
        return tv3;
    }

    public String getTv4() {
        return tv4;
    }

    public String getTv5() {
        return tv5;
    }

    public String getIvLink() {
        return ivLink;
    }

    public int getSound() {
        return sound;
    }
}


