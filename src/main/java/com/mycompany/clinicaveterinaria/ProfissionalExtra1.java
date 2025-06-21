package com.mycompany.clinicaveterinaria;

public class ProfissionalExtra extends User
{
    String name, email, turno;
    int cpf, phoneNumber;

    public ProfissionalExtra(String name, String email, String turno,
            int cpf, int phoneNumber)
    {
        super(name, email, cpf, phoneNumber);
        this.turno = turno;
    }

    String getTurno(){
        return turno;
    }

    void setTurno(String turno){
        this.turno = turno;
    }
}
