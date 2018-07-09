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

    public String addCartDetails(){
        String add = "https://www.greenravolution.com/API/addcartdetails.php";
        return add;
    }

    public String deleteCartDetails(){
        String delete = "https://www.greenravolution.com/API/deletecartdetails.php";
        return delete;
    }

    public String editCartDetails(){
        String edit = "https://www.greenravolution.com/API/updatecartdetails.php";
        return edit;
    }

    public String addTransaction(){
        String add = "https://www.greenravolution.com/API/addtransaction.php";
        return add;
    }

    public String addTransactionDetails(){
        String add = "https://www.greenravolution.com/API/addtransactiondetails.php";
        return add;
    }

    public String getTransaction(){
        String add = "https://www.greenravolution.com/API/gettransaction.php";
        return add;
    }

    public String getTransactionDetails(){
        String add = "https://www.greenravolution.com/API/gettransactiondetails.php";
        return add;
    }

    public String getCategories(){
        String get = "https://www.greenravolution.com/API/getCategories.php";
        return get;
    }
    public String getEditUser(){
        return "https://www.greenravolution.com/API/updateuserdetails.php";
    }
    public String getUpdateImage(){
        return "https://www.greenravolution.com/API/updateimage.php";
    }
    public String getFacebookLogin(){
        return "https://www.greenravolution.com/API/facebookloginsignup.php";
    }
    public String getFAQ(){
        return "https://www.greenravolution.com/API/sendhelp.php";
    }

    public String getForgotPassword(){
        return "https://www.greenravolution.com/API/forgetpassword.php";
    }


}
