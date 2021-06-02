package hu.demo.vaccination.service;

public interface FileHandler<U, T> {
    boolean fileSave(U createData);

    U fileLoad(T input);
}
