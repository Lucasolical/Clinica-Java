package com.mycompany.clinicaveterinaria;

import java.util.ArrayList;

public class Animal
{
    String name, race;
    Time birthDate;
    Tutor tutor;
    ArrayList<Vacina> cartaoDeVacina = new ArrayList<Vacina>();
    ArrayList<Consulta> prontuario = new ArrayList<Consulta>();

    public Animal(String name, String race, Time birthDate, Tutor tutor){
        this.name = name;
        this.race = race;
        this.birthDate = birthDate;
        this.tutor = tutor;
    }
    public void incluirVacina(Vacina vac){
        cartaoDeVacina.add(vac);
    }

    //public void consulVac(Vacina vac){
    //    System.out.println("vacina do Tipo:" + vac.getTipo() + "valor: R$ "+ vac.getCusto());
    //}
    //public void imprimirVac(){
    //    for(Vacina vaci:cartaoDeVacina){
    //        System.out.println("vacina tipo:"+vaci.getTipo()+"de custo R$ "+vaci.getCusto());
    //    }
    //}

    public ArrayList<Vacina> getCartaoDeVacina(){
        return cartaoDeVacina;
    }

    public ArrayList<Vacina> vacinasParaVencerNoMes(Time currentTime){
        ArrayList<Vacina> vacinasVelhas = new ArrayList<Vacina>();

        for(Vacina vacina: cartaoDeVacina){
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
