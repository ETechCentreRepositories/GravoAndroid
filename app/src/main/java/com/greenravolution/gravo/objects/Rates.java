package com.greenravolution.gravo.objects;

/**
 * Created by user on 28/3/2018.
 */

public class Rates {
    private int id;
    private String type;
    private String rate;

    public Rates(int id, String type, String rate) {
        this.id = id;
        this.type = type;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
