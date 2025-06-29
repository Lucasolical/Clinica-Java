package com.mycompany.clinicaveterinaria;

import java.lang.*;

public abstract class User{
    String name, email, cpf, phoneNumber;

    public User(){
    }

    public User(String name, String email,
            String cpf, String phoneNumber)
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
    public String getCpf() {
        return cpf;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
