package com.mycompany.clinicaveterinaria;

public class Agendamento{
    Time dateAndTime;
    Specialty specialty;
    Animal animal;

    public Agendamento(Specialty specialty, Time dateAndTime, Animal animal){
        this.specialty = specialty;
        this.dateAndTime = dateAndTime;
        this.animal = animal;
    }

    public Time getDateAndTime(){
        return dateAndTime;
    }
    public Specialty getSpecialty(){
        return specialty;
    }

    public void setDateAndTime(Time dateAndTime){
        this.dateAndTime = dateAndTime;
    }
    public void setSpecialty(Specialty specialty){
        this.specialty = specialty;
    }
}
