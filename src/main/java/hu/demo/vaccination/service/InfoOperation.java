package hu.demo.vaccination.service;

public interface InfoOperation<T, U, V, W> extends CrudOperation<T, U> {

    V getInfo(int id);

    W getNameInfo(int id);
}
