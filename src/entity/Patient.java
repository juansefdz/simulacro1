package entity;

import java.util.Date;

public class Patient {
    private int id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String cardId;

    //constructor
    public Patient() {
    }
    public Patient(int id, String name, String lastName, Date birthDate, String cardId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cardId = cardId;
    }

    //get&set


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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    //ToString

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", cardId='" + cardId + '\'' +
                '}';
    }

    public String getCardId() {
        return this.cardId;
    }
}
