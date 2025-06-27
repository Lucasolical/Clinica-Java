package com.mycompany.clinicaveterinaria;

import java.lang.*;

public abstract class User{
    String name, email;
    int cpf, phoneNumber;

    public User(){
    }

    public User(String name, String email,
            int cpf, int phoneNumber)
    {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public void changeUser(User user){
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getCpf() {
        return cpf;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
