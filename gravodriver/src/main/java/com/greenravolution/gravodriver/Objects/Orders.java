package com.greenravolution.gravodriver.Objects;

/**
 * Created by user on 14/3/2018.
 */

public class Orders {
    private int id;
    private String transaction_code;
    private String transaction_type;
    private String address;
    private String postal;
    private int user_id;
    private int session_id;
    private int payment_id;
    private int status_id;
    private String collection_date;
    private String collecter_name;
    private String collector_number;

    public Orders(int id, String transaction_code, String transaction_type, String address, String postal, int user_id, int session_id, int payment_id, int status_id, String collection_date, String collecter_name, String collector_number) {
        this.id = id;
        this.transaction_code = transaction_code;
        this.transaction_type = transaction_type;
        this.address = address;
        this.postal = postal;
        this.user_id = user_id;
        this.session_id = session_id;
        this.payment_id = payment_id;
        this.status_id = status_id;
        this.collection_date = collection_date;
        this.collecter_name = collecter_name;
        this.collector_number = collector_number;
    }

    public Orders(int id, String transaction_code, String transaction_type, String address, String postal, int user_id, int session_id, int payment_id, int status_id) {
        this.id = id;
        this.transaction_code = transaction_code;
        this.transaction_type = transaction_type;
        this.address = address;
        this.postal = postal;
        this.user_id = user_id;
        this.session_id = session_id;
        this.payment_id = payment_id;
        this.status_id = status_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_code() {
        return transaction_code;
    }

    public void setTransaction_code(String transaction_code) {
        this.transaction_code = transaction_code;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getCollection_date() {
        return collection_date;
    }

    public void setCollection_date(String collection_date) {
        this.collection_date = collection_date;
    }

    public String getCollecter_name() {
        return collecter_name;
    }

    public void setCollecter_name(String collecter_name) {
        this.collecter_name = collecter_name;
    }

    public String getCollector_number() {
        return collector_number;
    }

    public void setCollector_number(String collector_number) {
        this.collector_number = collector_number;
    }
}

