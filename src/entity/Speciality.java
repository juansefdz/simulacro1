package entity;

import java.util.List;

public class Speciality {
    private int id;
    private String name;
    private String description;


    //constructor
    public Speciality() {
    }

    public Speciality(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //ToString

    @Override
    public String toString() {
        return "Speciality{" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }


    public Object findById(int idDelete) {
        return null;
    }

    public List findAll() {
        return null;
    }
}
