package com.greenravolution.gravo.objects;

public class API {
    public API() {
    }

    public String getLogin() {
        String login = "https://www.greenravolution.com/API/login.php";
        return login;
    }
    public String getRegister() {
        String register = "https://www.greenravolution.com/API/signup.php";
        return register;
    }
    public String getCart() {
        String register = "https://www.greenravolution.com/API/getcart.php?";
        return register;
    }


}
