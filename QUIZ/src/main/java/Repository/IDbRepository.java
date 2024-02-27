package Repository;

import Domain.entity;

public interface IDbRepository<T extends entity> extends IRepository<T> {
    void openConnection();
    void closeConnection();

    void createTable();

    void InitTable();
}
