package com.mycompany.clinicaveterinaria;

import java.util.ArrayList;

public class Animal
{
    String name, race;
    Time birthDate;
    Tutor tutor;
    ArrayList<VacinaAplicada> cartaoDeVacina = new ArrayList<VacinaAplicada>();
    ArrayList<Consulta> prontuario = new ArrayList<Consulta>();

    public Animal(String name, String race, Time birthDate, Tutor tutor){
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.tutor = tutor;
    }
    public void incluirVacina(VacinaAplicada vac){
        cartaoDeVacina.add(vac);
    }

    public ArrayList<VacinaAplicada> getCartaoDeVacina(){
        return cartaoDeVacina;
    }

    public ArrayList<VacinaAplicada> vacinasParaVencerNoMes(Time currentTime){
        ArrayList<VacinaAplicada> vacinasVelhas = new ArrayList<VacinaAplicada>();

        for(VacinaAplicada vacina: cartaoDeVacina){
            Time vacinaTime = vacina.getDataDeValidade();
            vacinaTime.sub(currentTime);
            Time month = new Time();
            month.setMonth((byte)1);
            if(vacinaTime.cmp(month) <= 0){
                vacinasVelhas.add(vacina);
            }
        }

        return vacinasVelhas;
    }

    public ArrayList<Consulta> getProntuario(){
        return prontuario;
    }

    public String getName(){
        return name;
    }
    public String getRace(){
        return race;
    }
    public Time getBirthDate(){
        return birthDate;
    }
    public Tutor getTutor(){
        return tutor;
    }
}
