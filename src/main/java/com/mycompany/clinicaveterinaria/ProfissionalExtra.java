package com.mycompany.clinicaveterinaria;

public class ProfissionalExtra extends User
{
    String turno;

    public ProfissionalExtra(String name, String email, String turno,
            String cpf, String phoneNumber)
    {
        super(name, email, cpf, phoneNumber);
        this.turno = turno;
    }

    public String getTurno(){
        return turno;
    }

    public void setTurno(String turno){
        this.turno = turno;
    }
}
