package entity;

public class Appointment {

    private int id;
    private int idPatient;
    private int idMedic;
    private String AppointmentTime;
    private String appointmentHour;
    private String motive;

    private Medic objMedic;
    private Patient objPatient;


    //constructor

    public Appointment() {
    }

    public Appointment(int id, int idPatient, int idMedic, String appointmentTime, String appointmentHour, String motive, Medic objMedic, Patient objPatient) {
        this.id = id;
        this.idPatient = idPatient;
        this.idMedic = idMedic;
        AppointmentTime = appointmentTime;
        this.appointmentHour = appointmentHour;
        this.motive = motive;
        this.objMedic = objMedic;
        this.objPatient = objPatient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(String appointmentHour) {
        this.appointmentHour = appointmentHour;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public Medic getObjMedic() {
        return objMedic;
    }

    public void setObjMedic(Medic objMedic) {
        this.objMedic = objMedic;
    }

    public Patient getObjPatient() {
        return objPatient;
    }

    public void setObjPatient(Patient objPatient) {
        this.objPatient = objPatient;
    }

    //toString

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idMedic=" + idMedic +
                ", AppointmentTime='" + AppointmentTime + '\'' +
                ", appointmentHour='" + appointmentHour + '\'' +
                ", motive='" + motive + '\'' +
                ", objMedic=" + objMedic +
                ", objPatient=" + objPatient +
                '}';
    }
}
