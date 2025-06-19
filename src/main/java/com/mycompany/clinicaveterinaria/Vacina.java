package com.mycompany.clinicaveterinaria;

public class Vacina 
{
    float custo;
    String tipo;
    Time dataFeita;
    int anosDeValidade; 
    
    public Vacina(String tipo, float custo, Time dataFeita, int tempoDeValidade) {
       this.tipo = tipo;
       this.custo = custo;
       this.dataFeita = dataFeita;
       this.anosDeValidade = tempoDeValidade;
    }
    
    public String getTipo(){
        return tipo;
    }

    public Time getDataFeita(){
        return dataFeita;
    }

    public Time getDataDeValidade(){
        Time dataDeValidade = null;
        try{
            dataDeValidade = (Time)dataFeita.clone();
        }
        catch(Exception e){
        }

        dataDeValidade.addYears(anosDeValidade);

        return dataDeValidade;
    }

    public int getValidadeEmAnos(){
        return anosDeValidade;
    }
    
    public float getCusto(){
        return custo;
    }
}
