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
    
    public void aplicarVacina(VacinaAplicada vac){
        animal.incluirVacina(vac);
        if(vac.getTipo() == null){
            return;
        }
        this.custoTotal += vac.getTipo().custo;
    }
}
