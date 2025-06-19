package com.mycompany.clinicaveterinaria;

import java.util.*;

public class Consulta
{
    float custoTotal;
    Time dateAndTime;
    Animal animal;
    Veterinario vet;
    String diagnostico;
    List<String> medicamentosPrescritos = new ArrayList<String>();
    
    public Consulta(Animal animal, Time dateAndTime, 
            Veterinario vet, String diagnostico, List<String> medicamentosPrescritos)
    {
        this.animal = animal;
        this.dateAndTime = dateAndTime;
    }
    
    public void aplicarVacina(Vacina vac){
        animal.incluirVacina(vac);
        this.custoTotal = this.custoTotal + vac.getCusto();
    }
    public void Cobrança(){
        System.out.println("valor da consulta: R$"+ custoTotal + 300);
    }
}
