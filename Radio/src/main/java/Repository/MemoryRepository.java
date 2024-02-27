package Repository;

import Domain.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

    public class MemoryRepository <T extends entity> implements IRepository<T>{
        List<T> entities = new ArrayList<T>();

        @Override
        public void add(T entity) throws TwiceException {
            if(entity == null) {
                throw new IllegalArgumentException("Entitatea nu poate fi nula!");
            }

            if(Find(entity.getId())!=null) {
                throw new TwiceException("Entitatea a mai fost deja introdusa");
            }
            entities.add(entity);

        }

        @Override
        public void delete(int id) {
            T entity_to_delete = null;
            for(T entity:entities){
                if(entity.getId() == id){
                    entity_to_delete = entity;
                }
            }
            entities.remove(entity_to_delete);
        }

        @Override
        public T Find(int id) {
            for(T entity:entities){
                if(entity.getId() == id){
                    return entity;
                }
            }
            return null;
        }

        @Override
        public void update(T entity) throws IOException {
            T entity_to_update = null;
            for(T e:entities){
                if(e.getId() == entity.getId()){
                    entity_to_update = e;
                }
            }
            entities.remove(entity_to_update);
            entities.add(entity);
        }

        @Override
        public Collection<T> getAll() {
            return new ArrayList<T>(entities);
        }

        @Override
        public Iterator<T> iterator() {
            return new ArrayList<T>(entities).iterator();
        }
    }


