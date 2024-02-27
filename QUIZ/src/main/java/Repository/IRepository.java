package Repository;

import Domain.entity;

import java.io.IOException;
import java.util.Collection;
    public interface IRepository <T extends entity> extends Iterable<T>{
        public void add(T entity) throws TwiceException;
        public void delete(int id);

        public T Find(int id);

        public void update(T entity) throws IOException;

        public Collection<T> getAll();
    }

