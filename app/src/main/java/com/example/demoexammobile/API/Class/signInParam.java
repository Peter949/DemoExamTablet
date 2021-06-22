package com.example.demoexammobile.API.Class;

//Класс токены для входа пользователя
public class signInParam
{
    private String email, password;
    //Аксессоры класса
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
