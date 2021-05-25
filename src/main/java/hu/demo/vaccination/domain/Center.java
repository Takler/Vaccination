package hu.demo.vaccination.domain;

import java.util.Objects;

public class Center {
    private int id;
    private String name;
    private String city;
    private String email;
    private String telephoneNumber;
    private int dailyCapacity;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(int dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Center center = (Center) o;
        return id == center.id && dailyCapacity == center.dailyCapacity && Objects.equals(name, center.name) && Objects.equals(city, center.city) && Objects.equals(email, center.email) && Objects.equals(telephoneNumber, center.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, email, telephoneNumber, dailyCapacity);
    }
}
