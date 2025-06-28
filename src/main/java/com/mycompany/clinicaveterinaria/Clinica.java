package com.mycompany.clinicaveterinaria;

import java.util.*;
import java.time.*;
import java.lang.reflect.*;

public class Clinica {
    public List<Specialty> specialties = new ArrayList<Specialty>();
    public List<Vacina> tiposVacinas = new ArrayList<Vacina>();

    List<User> users = new ArrayList<User>();
    List<Consulta> consultas= new ArrayList<Consulta>();
    List<Agendamento> agendamentos = new ArrayList<Agendamento>();

    public Specialty getSpecialty(String specialty){
        for(Specialty spec : specialties){
            if(specialty == spec.nome){
                return spec;
            }
        }

        return null;
    }

    public Vacina getVacina(String vacina){
        for(Vacina vac : tiposVacinas){
            if(vacina == vac.nome){
                return vac;
            }
        }

        return null;
    }

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

    public boolean agendar(String specialty, Time dateAndTime, Animal animal) {
        if(dateAndTime.getMinute()%20 != 0 ||
            !(dateAndTime.getHour() >= 8 && dateAndTime.getHour() <= 12) ||
            !(dateAndTime.getHour() >= 14 && dateAndTime.getHour() <= 18))
        {
            return false;
        }

        if(getAgendamento(dateAndTime) != null){
            return false;
        }

        Agendamento consulta = new Agendamento(specialty, dateAndTime, animal);

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

    public void addSpecialty(String nome, int price){
        for(Specialty s : specialties)
            if(s.nome == nome)
                return;

        specialties.add(new Specialty(nome, price));
    }

    public void addVacina(String nome, int price){
        for(Vacina v : tiposVacinas)
            if(v.nome == nome)
                return;

        tiposVacinas.add(new Vacina(nome, price));
    }

    public Clinica(){
        addSpecialty("Fisioterapeuta", 10);
        addSpecialty("Oftamologista", 20);
        addSpecialty("Pediatra", 5);
        addSpecialty("Otorrinolaringologista", 33);
        addSpecialty("Cardiologista", 15);

        addVacina("Febre amarela", 15);
        addVacina("Coronavírus", 5);
        addVacina("Câncer", 30);
        addVacina("Gripe", 8);
        addVacina("AIDS", 3);
    }
}
