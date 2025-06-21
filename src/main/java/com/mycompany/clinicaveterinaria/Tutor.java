import java.util.*;

public class Tutor extends User
{
    String address;
    List<Animal> animals = new ArrayList<Animal>();

    public Tutor(String name, String email, String address, 
            String phoneNumber, String cpf)
    {
        super(name, email, cpf, phoneNumber);
        this.address = address;
    }

    String getAddress(){
        return address;
    }

    void setAddress(String address){
        this.address = address;
    }
}
