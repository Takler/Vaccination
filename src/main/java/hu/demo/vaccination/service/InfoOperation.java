package hu.demo.vaccination.service;

public interface InfoOperation<T> {

    T getInfo(int id);

    T getNameInfo(int id);
}
