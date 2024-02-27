package Domain;

public class Intrebari extends entity {
    private String text;
    private String RaspunsA;

    private String RaspunsB;

    private String RaspunsC;

    private String RaspunsCorect;

    private int Punctaj;

    public Intrebari(int id, String text, String RaspunsA, String RaspunsB, String RaspunsC, String RaspunsCorect, int Punctaj){
         super(id);
         this.text = text;
         this.RaspunsA = RaspunsA;
         this.RaspunsB = RaspunsB;
         this.RaspunsC = RaspunsC;
         this.RaspunsCorect = RaspunsCorect;
         this.Punctaj = Punctaj;
    }

    public String getText(){
        return text;
    }

    public String getRaspunsA(){
        return RaspunsA;
    }

    public String getRaspunsB(){
        return RaspunsB;
    }
    public String getRaspunsC(){
        return RaspunsC;
    }

    public String getRaspunsCorect(){
        return RaspunsCorect;
    }

    public int getPunctaj(){
        return Punctaj;
    }



    public String toString(){
        return "Id: " + this.getId() + ", "+"text: " + text + ", " + "RaspunsA: " + RaspunsA +", " + "RaspunsB: " + RaspunsB + ", " + "RaspunsC: " + RaspunsC + ", " + "RaspunsCorect: " + RaspunsCorect + ", " + "Punctaj: " + Punctaj;
    }
}