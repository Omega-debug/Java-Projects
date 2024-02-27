package Service;

import Domain.Piesa;
import Repository.IRepository;
import Repository.TwiceException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class PiesaService {
    IRepository<Piesa> repository;

    public PiesaService(IRepository<Piesa> repository){
        this.repository = repository;
    }
    public List<Piesa> filter(String text) {
        String textLowerCase = text.toLowerCase();
        List<Piesa> auxList = repository.getAll().stream().filter(
                piesa -> {return (piesa.getFormatie().toLowerCase().contains(textLowerCase) ||
                        piesa.getTitlu().toLowerCase().contains(textLowerCase) || piesa.getGenMuzical().toLowerCase().contains(textLowerCase)  || piesa.getDurata().toLowerCase().contains(textLowerCase) );}).toList();
        if(auxList.isEmpty()) throw new RuntimeException("Filtrare fara rezultate!");
        return auxList;
    }

    public Collection<Piesa> getAll(){
        return repository.getAll();
    }

}

