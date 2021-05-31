package hu.demo.vaccination.service;

public interface InfoOperation<T, U> {

    T getInfo(int id);

    U getNameInfo(int id);
}
