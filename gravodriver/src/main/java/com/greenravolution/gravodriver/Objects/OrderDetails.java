package com.greenravolution.gravodriver.Objects;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by user on 14/3/2018.
 */

public class OrderDetails implements Serializable {
    private int id;
    private int transaction_id;
    private String weight;
    private String price;
    private int category_id;

    public OrderDetails(int id, int transaction_id, String weight, String price, int category_id) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.weight = weight;
        this.price = price;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String toString() {
        String string = "{";
        string += "'id':" + getId() + ",";
        string += "'transaction_id':" + getTransaction_id() + ",";
        string += "'cat_id':" + getCategory_id() + ",";
        string += "'weight':" + getWeight() + ",";
        string += "'price':" + getPrice() + "}";
        Log.i("Detail JSON String: ", string);
        return string;
    }
}
