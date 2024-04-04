package model;

import database.CRUD;
import database.configDB;
import entity.Appointment;

import java.sql.*;
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
            objPrepare.setTime(2, Time.valueOf(objAppointment.getCiteTime()));
            objPrepare.setString(3,objAppointment.getMotive());
            objPrepare.setInt(4,objAppointment.getIdPatient());
            objPrepare.setInt(4,objAppointment.getIdMedic());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while(objResult.next()){
                objAppointment.setId(objResult.getInt(1));
            }

        }catch (SQLException e){
            System.out.println("error-> "+e);
        }
    return null;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }
}
