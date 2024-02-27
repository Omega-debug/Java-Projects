package Service;

import Domain.Intrebari;
import Repository.IRepository;
import Repository.TwiceException;

import java.io.IOException;
import java.util.Collection;

public class IntrebareService {
    IRepository<Intrebari> repository;

    public IntrebareService(IRepository<Intrebari> repository){
        this.repository = repository;
    }

    public void add(int id, String text, String Ra, String Rb, String Rc, String Corect, int punctaj) throws TwiceException {
        repository.add(new Intrebari(id,text,Ra,Rb,Rc,Corect,punctaj));
    }


    public Intrebari Find(int id){
        return repository.Find(id);
    }

    public Collection<Intrebari> getAll(){
        return repository.getAll();
    }

}