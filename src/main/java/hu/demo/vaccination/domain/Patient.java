package hu.demo.vaccination.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private String mothersName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String city;
    private String zipCode;
    private String address;
    private String telephoneNumber;
    private boolean pregnant;
    private boolean underlyingMedicalCondition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public boolean isUnderlyingMedicalCondition() {
        return underlyingMedicalCondition;
    }

    public void setUnderlyingMedicalCondition(boolean underlyingMedicalCondition) {
        this.underlyingMedicalCondition = underlyingMedicalCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id && pregnant == patient.pregnant && underlyingMedicalCondition == patient.underlyingMedicalCondition && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(mothersName, patient.mothersName) && Objects.equals(gender, patient.gender) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(email, patient.email) && Objects.equals(city, patient.city) && Objects.equals(zipCode, patient.zipCode) && Objects.equals(address, patient.address) && Objects.equals(telephoneNumber, patient.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mothersName, gender, dateOfBirth, email, city, zipCode, address, telephoneNumber, pregnant, underlyingMedicalCondition);
    }
}
