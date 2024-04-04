package model;

import database.CRUD;
import database.configDB;
import entity.Appointment;
import entity.Medic;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AppointmentModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = configDB.openConnection();

        Appointment objAppointment=(Appointment) object;

        try {
            String sql ="INSERT INTO appointments (appointment_date,appointment_time,description,id_medic_appointment,id_patient_appointment) VALUES (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setDate(1,Date.valueOf(objAppointment.getAppointmentHour()));
            objPrepare.setTime(2, Time.valueOf(objAppointment.getAppointmentTime()));
            objPrepare.setString(3,objAppointment.getMotive());
            objPrepare.setInt(4,objAppointment.getIdMedic());
            objPrepare.setInt(5,objAppointment.getIdPatient());


            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while(objResult.next()){
                objAppointment.setId(objResult.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"registro exitoso");

        }catch (SQLException e){
            System.out.println("error-> "+e);
        }
    return null;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = configDB.openConnection();

        List<Object> listAppointment = new ArrayList<>();

        try {
            String sql = "SELECT * FROM appointments \n"+"INNER JOIN patients ON patients.id_patient = appointments.id_patient_appointment \n"+"INNER JOIN medics ON medics.id_medic = appointment.id_medic_appointment;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){

                Appointment objAppointment = new Appointment();
                Medic objMedic = new Medic();
                Patient objPatient = new Patient();




                objAppointment.setId(objResult.getInt("appointments.id_appointment"));
                objAppointment.setAppointmentTime(objResult.getString("appointments.appointment_time"));
                objAppointment.setAppointmentHour(objResult.getString("appointments.appointment_date"));
                objAppointment.setMotive(objResult.getString("appointments.description"));
                objAppointment.setIdPatient(objResult.getInt("appointments.id_patient_appointment"));
                objAppointment.setIdMedic(objResult.getInt("appointments.id_medic_appointment"));

                objMedic.setName(objResult.getString("medics.medic_name"));
                objPatient.setName(objResult.getString("patients.patient_name"));

                objAppointment.setObjMedic(objMedic);
                objAppointment.setObjPatient(objPatient);
                listAppointment.add(objAppointment);
            }



        }catch (SQLException e){
            System.out.println("error"+e.getMessage());
        }
        configDB.closeConnection();
        return listAppointment;
    }

    @Override
    public boolean delete(Object object) {
        Connection objConnection = configDB.openConnection();
        Appointment objAppointment = (Appointment)object;
        boolean isDeleted =false;

        try {
            String sql ="DELETE FROM appointments WHERE appointments.id_appointment=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAppointment.getId());
            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected>0){
                isDeleted=true;
                JOptionPane.showMessageDialog(null,"registro eliminado correctamente");
            }
        }catch (SQLException e){
            System.out.println("error:"+e.getMessage());
        }
        configDB.closeConnection();
        return isDeleted;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = configDB.openConnection();
        Appointment objAppointment = (Appointment)object;
        boolean isUpdated = false;
        try {
            String sql = "UPDATE appointment set appointment_date=?,appointment_time=?,description=?,id_medic_appointment =?,id_patient_appointment=3; WHERE id_appointment=? ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1,Date.valueOf(objAppointment.getAppointmentHour()));
            objPrepare.setTime(2, Time.valueOf(objAppointment.getAppointmentTime()));
            objPrepare.setString(3,  objAppointment.getMotive());
            objPrepare.setInt(4,  objAppointment.getIdPatient());
            objPrepare.setInt(5,  objAppointment.getIdMedic());
            objPrepare.setInt(6,  objAppointment.getId());

            int totalRowAffected =objPrepare.executeUpdate();
            if (totalRowAffected>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"registro eliminado correctamente");
            }

        }catch (SQLException e)
        {

            System.out.println("error"+e.getMessage());
        }
        configDB.closeConnection();
        return isUpdated;
    }
}
