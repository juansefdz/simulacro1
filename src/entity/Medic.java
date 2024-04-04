package entity;

public class Medic {

    private int id;
    private String name;
    private String lastName;
    private int idSpeciality;
    private Speciality objSpeciality;


    //constructor
    public Medic() {
    }

    public Medic(int id, String name, String lastName, int idSpeciality, Speciality objSpeciality) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idSpeciality = idSpeciality;

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

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public Speciality getObjSpeciality() {
        return objSpeciality;
    }

    public void setObjSpeciality(Speciality objSpeciality) {
        this.objSpeciality = objSpeciality;
    }
//toString


    @Override
    public String toString() {
        return "Medic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idSpeciality=" + idSpeciality +
                ", objSpeciality=" + objSpeciality.getName() +
                '}';
    }
}
