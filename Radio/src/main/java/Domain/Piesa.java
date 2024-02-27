package Domain;
import Domain.entity;
public class Piesa extends entity{
    private String Formatie;
    private String Titlu;
    private String GenMuzical;
    private String durata;


    public Piesa(int id, String Formatie, String Titlu, String GenMuzical, String durata){
        super(id);
        this.Formatie = Formatie;
        this.Titlu = Titlu;
        this.GenMuzical = GenMuzical;
        this.durata = durata;

    }

    public String getFormatie(){
        return Formatie;
    }

    public String getTitlu(){
        return Titlu;
    }

    public String getGenMuzical(){
        return GenMuzical;
    }

    public String getDurata(){
        return durata;
    }

    public String toString(){
        return "Id: " + this.getId() + ", "+"Formatie: " + Formatie + ", " + "Titlu: " + Titlu + "GenMuzical: " + GenMuzical+ "Durata: " + durata;
    }
}
