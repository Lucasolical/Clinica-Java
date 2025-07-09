package com.mycompany.clinicaveterinaria;

import java.util.ArrayList;

public class Animal
{
    String name, race;
    String birthDate;
    Tutor tutor;
    ArrayList<VacinaAplicada> cartaoDeVacina = new ArrayList<VacinaAplicada>();
    ArrayList<Consulta> prontuario = new ArrayList<Consulta>();

    public Animal(String name, String race, String birthDate, Tutor tutor){
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.tutor = tutor;
    }

    public Animal(String name, String race, String birthDate){
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
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
            month.setMonthInMinutes((byte)1);
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
    public String getBirthDate(){
        return birthDate;
    }
    public VacinaAplicada getVacina(String a){
        for(VacinaAplicada v : cartaoDeVacina){
            if(a == v.getTipo().nome){
                return v;
            }
        }
        return null;
    }
    public Tutor getTutor(){
        return tutor;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public void setRace(String race){
        this.race = race;
    }
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }
}
