package hu.demo.vaccination.service;

import java.util.List;

public interface CrudOperation<T, U> {
    public T getById(int id);
}
