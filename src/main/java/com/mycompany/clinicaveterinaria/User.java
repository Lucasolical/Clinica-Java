package com.mycompany.clinicaveterinaria;

import java.lang.*;

public abstract class User{
    String name, email;
    long cpf, phoneNumber;

    public User(){
    }

    public User(String name, String email,
            long cpf, long phoneNumber)
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
    public long getCpf() {
        return cpf;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
