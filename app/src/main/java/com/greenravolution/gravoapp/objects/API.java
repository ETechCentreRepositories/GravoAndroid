package com.greenravolution.gravoapp.objects;

public class API {
    public API() {
    }

    public String getLogin() {
        String login = "http://ehostingcentre.com/gravo/login.php";
        return login;
    }
    public String getRegister() {
        String register = "http://ehostingcentre.com/gravo/signup.php";
        return register;
    }
    public String getCart() {
        String register = "http://ehostingcentre.com/gravo/getcart.php?";
        return register;
    }

    public String addCartDetails(){
        String add = "http://ehostingcentre.com/gravo/addcartdetails.php";
        return add;
    }

    public String deleteCartDetails(){
        String delete = "http://ehostingcentre.com/gravo/deletecartdetails.php";
        return delete;
    }

    public String editCartDetails(){
        String edit = "http://ehostingcentre.com/gravo/updatecartdetails.php";
        return edit;
    }

    public String addTransaction(){
        String add = "http://ehostingcentre.com/gravo/addtransaction.php";
        return add;
    }

    public String addTransactionDetails(){
        String add = "http://ehostingcentre.com/gravo/addtransactiondetails.php";
        return add;
    }

    public String getTransaction(){
        String add = "http://ehostingcentre.com/gravo/gettransaction.php";
        return add;
    }

    public String getTransactionDetails(){
        String add = "http://ehostingcentre.com/gravo/gettransactiondetails.php";
        return add;
    }

    public String getCategories(){
        String get = "http://ehostingcentre.com/gravo/getCategories.php";
        return get;
    }
    public String getEditUser(){
        return "http://ehostingcentre.com/gravo/updateuserdetails.php";
    }
    public String getUpdateImage(){
        return "http://ehostingcentre.com/gravo/updateimage.php";
    }
    public String getFacebookLogin(){
        return "http://ehostingcentre.com/gravo/facebookloginsignup.php";
    }
    public String getFAQ(){
        return "http://ehostingcentre.com/gravo/sendhelp.php";
    }

    public String getForgotPassword(){
        return "http://ehostingcentre.com/gravo/forgetpassword.php";
    }


}
