package com.mycompany.clinicaveterinaria;

import java.util.*;

public class Consulta
{
    float custoTotal;
    Time dateAndTime;
    Animal animal;
    Veterinario vet;
    String diagnostico;
    String problema;
    String medicamentosPrescritos ;
    
    public Consulta(Animal animal, Time dateAndTime, 
            Veterinario vet, String diagnostico, String medicamentosPrescritos,
            String problema)
    {
        this.animal = animal;
        this.dateAndTime = dateAndTime;
        this.problema = problema;
        this.medicamentosPrescritos = medicamentosPrescritos;
    }
    
    public void aplicarVacina(VacinaAplicada vac){
        animal.incluirVacina(vac);
        if(vac.getTipo() == null){
            return;
        }
        this.custoTotal += vac.getTipo().custo;
    }
}
