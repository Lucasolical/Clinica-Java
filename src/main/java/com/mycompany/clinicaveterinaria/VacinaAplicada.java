package com.mycompany.clinicaveterinaria;

public class VacinaAplicada 
{
    Vacina tipo;
    Time dataFeita;
    int anosDeValidade; 
    
    public VacinaAplicada(Vacina tipo, Time dataFeita, int tempoDeValidade) {
        this.tipo = tipo;
        this.dataFeita = dataFeita;
        this.anosDeValidade = tempoDeValidade;
    }
    
    public Vacina getTipo(){
        return tipo;
    }

    public Time getDataFeita(){
        return dataFeita;
    }

    public Time getDataDeValidade(){
        Time dataDeValidade = new Time();
        dataDeValidade.time = dataFeita.time;


        dataDeValidade.addYears(anosDeValidade);

        return dataDeValidade;
    }

    public int getValidadeEmAnos(){
        return anosDeValidade;
    }
}
