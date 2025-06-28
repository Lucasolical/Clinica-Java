package com.mycompany.clinicaveterinaria;

public class Agendamento{
    Time dateAndTime;
    String specialty;
    Animal animal;

    public Agendamento(String specialty, Time dateAndTime, Animal animal){
        this.specialty = specialty;
        this.dateAndTime = dateAndTime;
        this.animal = animal;
    }

    public Time getDateAndTime(){
        return dateAndTime;
    }
    public String getSpecialty(){
        return specialty;
    }

    public void setDateAndTime(Time dateAndTime){
        this.dateAndTime = dateAndTime;
    }
    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }
}
