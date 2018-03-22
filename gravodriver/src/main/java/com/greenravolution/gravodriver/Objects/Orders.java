package com.greenravolution.gravodriver.Objects;

/**
 * Created by user on 14/3/2018.
 */

public class Orders {
    private String title;
    private String address;
    private String postal;
    private String timing;
    private int id;
    private int status_id;
    private String transaction_id;

    public Orders(int id, String transaction_id, String title, String address, String postal, String timing, int status_id) {
        this.title = title;
        this.address = address;
        this.postal = postal;
        this.timing = timing;
        this.id = id;
        this.status_id = status_id;
        this.transaction_id = transaction_id;
    }



    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

