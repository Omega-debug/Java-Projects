package Domain;

import java.io.Serializable;

public class entity implements Serializable {
    private static final long serialVersionUID = 1000L;
    private final int id;

    public entity(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
}