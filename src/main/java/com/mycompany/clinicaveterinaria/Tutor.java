package com.mycompany.clinicaveterinaria;

import java.util.*;

public class Tutor extends User
{
    String name, email, address;
    int cpf, phoneNumber;  
    List<Animal> animals = new ArrayList<Animal>();

    public Tutor(String name, String email, String address, 
            int phoneNumber, int cpf)
    {
        super(name, email, cpf, phoneNumber);
        this.address = address;
    }

    String getAddress(){
        return address;
    }

    void setAddress(String address){
        this.address = address;
    }
}
