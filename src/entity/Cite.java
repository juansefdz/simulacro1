package entity;

import java.util.Date;

public class Cite {

    private int id;
    private int idPatient;
    private int idMedic;
    private Date citeTime;
    private Date appointmentHour;

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

    private Medic objMedic;
    private Patient objPatient;


    //constructor
    public Cite() {
    }

    public Cite(int id, int idPatient, int idMedic, Date citeTime, Date appointmentHour, Medic objMedic, Patient objPatient) {
        this.id = id;
        this.idPatient = idPatient;
        this.idMedic = idMedic;
        this.citeTime = citeTime;
        this.appointmentHour = appointmentHour;
        this.objMedic = objMedic;
        this.objPatient = objPatient;
    }

    //get&set

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

    public Date getCiteTime() {
        return citeTime;
    }

    public void setCiteTime(Date citeTime) {
        this.citeTime = citeTime;
    }

    public Date getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(Date appointmentHour) {
        this.appointmentHour = appointmentHour;
    }

    //toString


    @Override
    public String toString() {
        return "Cite{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idMedic=" + idMedic +
                ", citeTime=" + citeTime +
                ", appointmentHour=" + appointmentHour +
                ", objMedic=" + objMedic +
                ", objPatient=" + objPatient +
                '}';
    }
}
