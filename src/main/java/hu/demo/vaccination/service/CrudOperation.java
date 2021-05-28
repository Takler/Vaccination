package hu.demo.vaccination.service;

import java.util.List;

public interface CrudOperation<T, U> {
    T getById(int id);

    List<T> findAll();

    boolean save(U createData);

    boolean update(int id, U createData);

    boolean delete(int id);
}
