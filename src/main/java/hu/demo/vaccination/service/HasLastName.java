package hu.demo.vaccination.service;

import java.util.List;

public interface HasLastName extends Requestable{
    public List<String> getLastName(String firstName);
}
