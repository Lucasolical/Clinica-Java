package com.mycompany.clinicaveterinaria;

public class Veterinario extends User
{
    Specialty specialty;
    String cfmv;

    public Veterinario( String name,
            String email,
            Specialty specialty,
            long cpf,
            long phoneNumber,
            String cfmv) 
    {
        super(name, email, cpf, phoneNumber);
        this.specialty = specialty;
        this.cfmv = cfmv;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
    public String getCfmv() {
        return cfmv;
    }

    public void setPhone(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    public void setCfmv(String cfmv) {
        this.cfmv = cfmv;
    }
}

