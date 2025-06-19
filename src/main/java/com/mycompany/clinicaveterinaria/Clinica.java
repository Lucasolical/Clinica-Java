package com.mycompany.clinicaveterinaria;

import java.util.*;
import java.time.*;
import java.lang.reflect.*;

public class Clinica
{
    List<User> users = new ArrayList<User>();
    List<Consulta> consultas= new ArrayList<Consulta>();
    List<Agendamento> agendamentos = new ArrayList<Agendamento>();

    public boolean cpfIsUnique(int cpf){
        for(User user : users){
            if(user.getCpf() == cpf){
                return false;
            }
        }
        return true;
    }

    public boolean signUser(User user) {
        if(!cpfIsUnique(user.cpf))
            return false;

        users.add(user);

        return true;
    }

    public User getUser(int cpf){
        for(User user : users){
            if(user.cpf == cpf){
                return user;
            }
        }

        return null;
    }

    //Fazer uma versão com deep copy
    public User getUserCopy(int cpf){
        User userCopy = null;

        try{
            userCopy = (User)getUser(cpf).clone();
        }catch(Exception e){
            return null;
        }

        return userCopy;
    }

    public boolean alterarUser(User user, int cpf){
        if(user.cpf != cpf)
            return false;

        if(cpfIsUnique(user.cpf)){
            return false;
        }

        //Contando com sobrescrição de método
        user.changeUser(user);

        return true;
    }

    public boolean agendar(String specialty, Time dateAndTime) {
        if(dateAndTime.getMinute()%20 != 0 ||
            !(dateAndTime.getHour() >= 8 && dateAndTime.getHour() <= 12) ||
            !(dateAndTime.getHour() >= 14 && dateAndTime.getHour() <= 18))
        {
            return false;
        }

        if(getAgendamento(dateAndTime) != null){
            return false;
        }

        Agendamento consulta = new Agendamento(specialty, dateAndTime);

        agendamentos.add(consulta);

        return true;
    }

    public Agendamento getAgendamento(Time dateAndTime){
        for(Agendamento agendamento : agendamentos)
        {
            if(agendamento.dateAndTime == dateAndTime)
            {
                return agendamento;
            }
        }

        return null;
    }

    public boolean criarConsulta(Animal animal, Veterinario veterinario, String problema, 
            String diagnostico, List<String> medicamentos, Time dateAndTime) 
    {
        if(dateAndTime.getMinute()%20 != 0 ||
            !(dateAndTime.getHour() >= 8 && dateAndTime.getHour() <= 12) ||
            !(dateAndTime.getHour() >= 14 && dateAndTime.getHour() <= 18))
        {
            return false;
        }

        if(getConsulta(dateAndTime) != null){
            return false;
        }

        Consulta consulta = new Consulta(animal, dateAndTime, veterinario, 
                diagnostico, medicamentos);

        consultas.add(consulta);

        return true;
    }

    public Consulta getConsulta(Time dateAndTime){
        for(Consulta consulta : consultas)
        {
            if(consulta.dateAndTime == dateAndTime)
            {
                return consulta;
            }
        }

        return null;
    }
}
