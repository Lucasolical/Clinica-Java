package com.mycompany.clinicaveterinaria;

public class Agendamento{
    Time dateAndTime = new Time();
    Specialty specialty;
    Animal animal;

    public Agendamento(Specialty specialty, Time dateAndTime, Animal animal){
        if(dateAndTime == null){
            System.out.println("Horário nulo");
            return;
        }
        this.specialty = specialty;
        this.dateAndTime.time = dateAndTime.time;
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
