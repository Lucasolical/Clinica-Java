package com.mycompany.clinicaveterinaria;

public class Veterinario extends User
{
    String name, email, specialty;
    int cpf, phoneNumber, cfmv;

    public Veterinario( String name,
            String email,
            String specialty,
            int cpf,
            int phoneNumber,
            int cfmv) 
    {
        super(name, email, cpf, phoneNumber);
        this.specialty = specialty;
        this.cfmv = cfmv;
    }

    public String getSpecialty() {
        return specialty;
    }
    public int getCfmv() {
        return cfmv;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public void setCfmv(int cfmv) {
        this.cfmv = cfmv;
    }
}

