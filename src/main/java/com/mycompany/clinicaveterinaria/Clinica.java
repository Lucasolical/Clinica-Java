package com.mycompany.clinicaveterinaria;

import java.util.*;
import java.time.*;
import java.lang.reflect.*;

public class Clinica {
    public Time currentTime = new Time(2025, 0, 0, 0, 0);

    public List<Specialty> specialties = new ArrayList<Specialty>();
    public List<Vacina> tiposVacinas = new ArrayList<Vacina>();

    List<User> users = new ArrayList<User>();
    List<Consulta> consultas= new ArrayList<Consulta>();
    List<Agendamento> agendamentos = new ArrayList<Agendamento>();

    public ArrayList<String> getSpecialtiesAsStrings(){
        ArrayList<String> strings = new ArrayList<>();
        for(Specialty s : specialties){
            strings.add(s.nome);
        }
        return strings;
    }

    public ArrayList<String> getVacinasAsStrings(){
        ArrayList<String> strings = new ArrayList<>();
        for(Vacina s : tiposVacinas){
            strings.add(s.nome);
        }

        return strings;
    }

    public Specialty getSpecialty(String specialty){
        for(Specialty spec : specialties){
            if(specialty == spec.nome){
                return spec;
            }
        }

        return null;
    }

    public Veterinario getVet(Specialty spec){
        for (User a : users) {
            if (a instanceof Veterinario) {
                Veterinario vet = (Veterinario) a;
                if(vet.specialty == spec){
                    return vet;
                }
            }
        }

        return null;
    }
    public Veterinario getVet(String s){
        for (User a : users) {
            if (a instanceof Veterinario) {
                Veterinario vet = (Veterinario) a;
                if(vet.getName() == s){
                    return vet;
                }
            }
        }

        return null;
    }

    public Vacina getVacina(String vacina){
        for(Vacina vac : tiposVacinas){
            if(vacina.equals(vac.nome)){
                return vac;
            }
        }

        return null;
    }

    public boolean cpfIsUnique(String cpf){
        for(User user : users){
            if(user.getCpf().equals(cpf)){
                return false;
            }
        }

        return true;
    }

    public void deleteUser(User user){
        users.remove(user);
    }

    public boolean signUser(User user) {
        if(!cpfIsUnique(user.cpf))
            return false;

        users.add(user);

        return true;
    }

    public User getUser(String cpf){
        for(User user : users){
            if(user.cpf.equals(cpf)){
                return user;
            }
        }

        return null;
    }

    public boolean agendar(Specialty specialty, Time dateAndTime, Animal animal) {
        if(dateAndTime == null){
            System.out.println("Data é nulo");
            return false;
        }
        if(dateAndTime.time < currentTime.time){
            System.out.println("Data inválida.");
            currentTime.print();
            dateAndTime.print();
            return false;
        }
        if(dateAndTime.getMinute()%20 != 0 ||
            !(dateAndTime.getHour() >= 8 && dateAndTime.getHour() <= 12 ||
            dateAndTime.getHour() >= 14 && dateAndTime.getHour() <= 18))
        {
            System.out.println("Hora inválida.");
            return false;
        }

        if(!freeTime(dateAndTime)){
            System.out.println("Horário ocupado.");
            return false;
        }

        Agendamento consulta = new Agendamento(specialty, dateAndTime, animal);

        agendamentos.add(consulta);

        return true;
    }

    public Agendamento getAgendamento(Time dateAndTime){
        for(Agendamento agendamento : agendamentos)
        {
            if(agendamento.dateAndTime.time == dateAndTime.time)
            {
                return agendamento;
            }
        }

        return null;
    }

    public void cancelarAgendamento(Time time){
        Agendamento a = getAgendamento(time);
        if(a != null)
            agendamentos.remove(a);
    }

    public List<Veterinario> getVets(){
        List<Veterinario> vets = new ArrayList<>();
        for (User a : users) {
            if (a instanceof Veterinario) {
                vets.add((Veterinario)a);
            }
        }

        return vets;
    }

    public boolean criarConsulta(Animal animal, Veterinario veterinario, String problema, 
            String diagnostico, String medicamentos, Time dateAndTime) 
    {
        if(dateAndTime.getMinute()%20 != 0 ||
            (!(dateAndTime.getHour() >= 8 && dateAndTime.getHour() <= 12) &&
            !(dateAndTime.getHour() >= 14 && dateAndTime.getHour() <= 18)))
        {
            System.out.println("A consulta não condiz com os tempos de funcionamento da clínica");
            return false;
        }

        if(getConsulta(dateAndTime) != null){
            System.out.println("Já há uma consulta feita nesse horário");
            return false;
        }

        Consulta consulta = new Consulta(animal, dateAndTime, veterinario, 
                diagnostico, medicamentos, problema);

        consultas.add(consulta);

        return true;
    }

    public List<Agendamento> getTutorAgendamentos(Tutor tutor){
        List<Agendamento> localAgendamentos = new ArrayList<>();
        for(Agendamento a : agendamentos){
            if(a.animal.tutor == tutor){
                localAgendamentos.add(a);
            }
        }

        return localAgendamentos;
    }

    public Consulta getConsulta(Time dateAndTime){
        for(Consulta consulta : consultas)
        {
            if(consulta.dateAndTime.time == dateAndTime.time)
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

    public boolean freeTime(Time time){
        for(Agendamento a : agendamentos){
            if(a.getDateAndTime().cmp(time) == 0){
                return false;
            }
        }

        for(Consulta a : consultas){
            if(a.dateAndTime.cmp(time) == 0){
                return false;
            }
        }

        return true;
    }

    //A parte das horas vão ser ignoradas
    public List<Time> getFreeTimes(Time time){
        time.setHourInMinutes(0);
        time.setMinute(0);
        List<Time> allTimes = new ArrayList<Time>();
        Time firstBreak = new Time(time);
        firstBreak.setHourInMinutes(12);
        Time curTime = new Time(time);
        for(curTime.setHourInMinutes(8);
                curTime.time < firstBreak.time;
                curTime.addMinutes(20))
        {
            if(freeTime(curTime)){
                allTimes.add(new Time(curTime));
            }
        }
        time.setMinute(0);

        Time secondBreak = new Time(time);
        secondBreak.setHourInMinutes(18);
        for(curTime.setHourInMinutes(14);
            curTime.time  < secondBreak.time;
            curTime.addMinutes(20))
        {
            if(freeTime(curTime)){
                allTimes.add(new Time(curTime));
            }
        }


        return allTimes;
    }

    public void addVacina(String nome, int price){
        for(Vacina v : tiposVacinas)
            if(v.nome == nome)
                return;

        tiposVacinas.add(new Vacina(nome, price));
    }

    public Clinica(){
        addSpecialty("Fisioterapeuta", 10);
        addSpecialty("Oftalmologista", 20);
        addSpecialty("Pediatra", 5);
        addSpecialty("Otorrinolaringologista", 33);
        addSpecialty("Cardiologista", 15);

        addVacina("Febre amarela", 15);
        addVacina("Coronavírus", 5);
        addVacina("Câncer", 30);
        addVacina("Gripe", 8);
        addVacina("AIDS", 3);

        List<Veterinario> veterinarios = new ArrayList<Veterinario>();
        veterinarios.add(new Veterinario("Lucas", "lucas@gmail.com",
                    getSpecialty("Fisioterapeuta"), "321",
                    "71903574839", "SP-12345"));
        veterinarios.add(new Veterinario("Mariana", "MarianaSouza@gmail.com",
                    getSpecialty("Oftamologista"), "84012345678",
                    "71999998888", "RJ-54321"));
        veterinarios.add(new Veterinario("Carlos", "CarlosVet@hotmail.com",
                    getSpecialty("Pediatra"), "65098765432",
                    "71912345678", "MG-11223"));
        veterinarios.add(new Veterinario("Fernanda", "FernandaClinica@vet.com",
                    getSpecialty("Otorrinolaringologista"), "52087654321",
                    "71987654321", "BA-33445"));
        veterinarios.add(new Veterinario("André", "AndreTeixeira@gmail.com",
                    getSpecialty("Cardiologista"), "71019283746",
                    "71965432109", "RS-55667"));
        veterinarios.add(new Veterinario("Juliana", "JulianaLima@veterinaria.com",
                    getSpecialty("Fisioterapeuta"), "39056473829",
                    "71932109876", "PR-77889"));
        veterinarios.add(new Veterinario("Ricardo", "RicardoVet@clinvet.com",
                    getSpecialty("Oftamologista"), "88013579246",
                    "71911223344", "PE-99001"));

        for(Veterinario t : veterinarios){
            signUser(t);
        }

       List<ProfissionalExtra> profissionaisExtras = new ArrayList<ProfissionalExtra>(); 

       profissionaisExtras.add(new ProfissionalExtra("Ana Paula", "AnaPaula@gmail.com", "Manhã", 
                   "12345678901", "71987654321"));
       profissionaisExtras.add(new ProfissionalExtra("Bruno Silva", "BrunoSilva@hotmail.com", "Tarde", 
                   "23456789012", "71912345678"));
       profissionaisExtras.add(new ProfissionalExtra("Carla Mendes", "CarlaMendes@outlook.com", "Noite", 
                   "34567890123", "71923456789"));
       profissionaisExtras.add(new ProfissionalExtra("Diego Rocha", "DiegoRocha@gmail.com", "Manhã", 
                   "45678901234", "71934567890"));
       profissionaisExtras.add(new ProfissionalExtra("Elisa Costa", "ElisaCosta@yahoo.com", "Tarde", 
                   "56789012345", "71945678901"));
       profissionaisExtras.add(new ProfissionalExtra("Felipe Nunes", "FelipeNunes@terra.com", "Noite", 
                   "67890123456", "71956789012"));


       List<Tutor> tutores = new ArrayList<Tutor>();
       tutores.add(new Tutor("marcos", "marcos@gmal.com", 
                   "São paulo", "71994404034", "123"));
       tutores.add(new Tutor("antônio", "antonio@gmal.com", 
                   "Rio de janeiro - copacabana", "71894214334", "7941181270"));
       tutores.add(new Tutor("Julia", "julia@gmal.com", 
                   "Juazeiro - BA", "64294212334", "92184302312"));

       for(Tutor t : tutores){
           signUser(t);
       } 
       tutores.get(0).addAnimal(new Animal("rex", "puddle", "2004, 4, 7, 8, 40"));
       tutores.get(0).addAnimal(new Animal("lola", "puddle", 
                   "new Time(2004, 4, 7, 8, 40"));
       tutores.get(0).addAnimal(new Animal("bingus", "sphynx", 
                   "208, 4, 7, 8, 40"));

       tutores.get(1).addAnimal(new Animal("mel", "labrador",
                   "2016, 11, 12, 9, 15"));
       tutores.get(1).addAnimal(new Animal("toby", "bulldog francês",
                   "2018, 6, 3, 10, 30"));
       tutores.get(1).addAnimal(new Animal("mimi", "persa",
               "2020, 2, 21, 14, 45"));
       tutores.get(1).addAnimal(new Animal("bella", "golden retriever",
                   "2021, 3, 14, 16, 25"));

       tutores.get(2).addAnimal(new Animal("nina", "beagle",
                   "2017, 9, 29, 11, 0"));
       tutores.get(2).addAnimal(new Animal("luke", "gato siamês",
                   "2019, 7, 8, 13, 10"));

       if(!agendar(getSpecialty("Oftamologista"), new Time(2026, 4, 3, 8, 20), tutores.get(0).animals.get(0))){ System.out.println("didn't worked"); }
       if(!agendar(getSpecialty("Pediatra"), new Time(2026, 4, 3, 8, 40), tutores.get(0).animals.get(1))){ System.out.println("Didn't worked"); }
       if(!agendar(getSpecialty("Fisioterapeuta"), new Time(2026, 4, 3, 9, 0), tutores.get(0).animals.get(2))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Fisioterapeuta"), new Time(2026, 4, 4, 9, 20), tutores.get(1).animals.get(0))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Otorrinolaringologista"), new Time(2026, 4, 4, 9, 40), tutores.get(1).animals.get(1))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Cardiologista"), new Time(2026, 4, 4, 10, 0), tutores.get(1).animals.get(2))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Cardiologista"), new Time(2026, 4, 4, 10, 20), tutores.get(1).animals.get(3))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Pediatra"), new Time(2026, 4, 5, 10, 40), tutores.get(2).animals.get(0))){System.out.println("Didn't worked");}
       if(!agendar(getSpecialty("Otorrinolaringologista"), new Time(2026, 4, 5, 11, 0), tutores.get(2).animals.get(1))){System.out.println("Didn't worked");}
    }
}
