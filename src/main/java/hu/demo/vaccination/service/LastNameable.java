package hu.demo.vaccination.service;

import java.util.List;

public interface LastNameable extends Nameable {
    public List<String> getLastName(String firstName);
}
