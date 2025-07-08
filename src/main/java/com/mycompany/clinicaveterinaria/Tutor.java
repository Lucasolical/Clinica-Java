package com.mycompany.clinicaveterinaria;

import java.util.*;

public class Tutor extends User
{
    String address;
    List<Animal> animals = new ArrayList<Animal>();

    public Tutor(String name, String email, String address, 
            String phoneNumber, String cpf)
    {
        super(name, email, cpf, phoneNumber);
        this.address = address;
    }

    public void addAnimal(Animal animal){
        animal.tutor = this;
        animals.add(animal);
    }

    public void removeAnimal(Animal animal){
        animals.remove(animal);
    }
    
    public Animal getAnimal(String name){
        for(Animal a : animals){
            if(name == a.name){
                return a;
            }
        }

        return null;
    }

    public List<Animal>getAnimals(){
        return animals;
    }
    public Animal getAnimal(String name){
        for(Animal a : animals){
            if(name.equals(a.name)){
                return a;
            }
        }

        return null;
    }
    String getAddress(){
        return address;
    }

    void setAddress(String address){
        this.address = address;
    }
}
