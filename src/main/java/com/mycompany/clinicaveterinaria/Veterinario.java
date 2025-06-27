package com.mycompany.clinicaveterinaria;

public class Veterinario extends User
{
    Specialty specialty;
    int cfmv;

    public Veterinario( String name,
            String email,
            Specialty specialty,
            int cpf,
            int phoneNumber,
            int cfmv) 
    {
        super(name, email, cpf, phoneNumber);
        this.specialty = specialty;
        this.cfmv = cfmv;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
    public int getCfmv() {
        return cfmv;
    }

    public void setPhone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public void setCfmv(int cfmv) {
        this.cfmv = cfmv;
    }
}

