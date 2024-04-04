package model;

import database.CRUD;
import database.configDB;
import entity.Patient;

import javax.swing.*;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class PatientModel implements CRUD {
    @Override
    public Object insert(Object object) {
        //open connection
        Connection objConnection = configDB.openConnection();

        Patient objPatient = (Patient) object;

        try {
            String sql = "INSERT INTO patients (patient_name,last_name_patient,birth_date, document_identification) VALUES (?,?,?,?);"; //SQL sentence

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, RETURN_GENERATED_KEYS)
                    ;
            //values to  query parameters
            objPrepare.setString(1, objPatient.getName());
            //values to  query parameters
            objPrepare.setString(2, objPatient.getLastName());
            //values to  query parameters
            objPrepare.setString(3, objPatient.getBirthDate().toString());
            //values to  query parameters
            objPrepare.setInt(4, Integer.parseInt(objPatient.getCardId()));

            //execute the query
            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objPatient.setId(objResult.getInt(1));
            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "patient has been create successful");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding author" + e.getMessage());
        }

        //close connection
        configDB.closeConnection();
        return objPatient;

    }
    @Override
    public List<Object> findAll() {
        List<Object> patients = new ArrayList<>();

        // Abrir la conexión
        Connection objConnection = configDB.openConnection();

        try {
            String sql = "SELECT * FROM patients;"; // Sentencia SQL para obtener todos los pacientes

            Statement objStatement = objConnection.createStatement();

            ResultSet objResult = objStatement.executeQuery(sql); // Ejecutar la consulta

            // Iterar sobre los resultados y agregar cada paciente a la lista
            while (objResult.next()) {
                int id = objResult.getInt("id_patient");
                String name = objResult.getString("patient_name");
                String lastName = objResult.getString("last_name_patient");
                Date birthDate = objResult.getDate("birth_date");
                String cardId = objResult.getString("document_identification");

                Patient patient = new Patient(id, name, lastName, birthDate, cardId);
                patients.add(patient);
            }

            objStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching patients: " + e.getMessage());
        }

        // Cerrar la conexión
        configDB.closeConnection();

        return patients;
    }

    @Override
    public boolean delete(Object object) {
        Patient objPatient = (Patient) object; //convert obj entity

        boolean deleteFlag = false;

        Connection objConnection = configDB.openConnection(); //open connection

        try {
            String sql = "DELETE FROM patients WHERE id_patient=?;"; //SQL sentence

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //prepare statement

            objPrepare.setInt(1, objPatient.getId()); //assign valor from SQL sentence

            int totalAffectedRows = objPrepare.executeUpdate(); //execute the query
            if (totalAffectedRows > 0) {  //validate if there are affected rows (deleted data)
                deleteFlag = true;
                JOptionPane.showMessageDialog(null, "deleted successful the speciality");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        configDB.closeConnection(); //close connection
        return false;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }


//specific methods
    public List<Patient> findByDocumentID(int document_id) {
    Connection objConnection = configDB.openConnection(); // abrir conexión
    List<Patient> patientByDocumentID = new ArrayList<>();

    String sql = "SELECT * FROM patients WHERE document_identification = ?"; // consulta SQL

    try {
        PreparedStatement objPrepare = objConnection.prepareStatement(sql); // preparar la consulta
        objPrepare.setInt(1, document_id); // establecer el valor del parámetro en la consulta

        ResultSet objResult = objPrepare.executeQuery(); // ejecutar la consulta

        // procesar los resultados y agregarlos a la lista
        while (objResult.next()) {
            Patient objPatient = new Patient();
            objPatient.setId(objResult.getInt("id_patient"));
            objPatient.setName(objResult.getString("patient_name"));
            objPatient.setLastName(objResult.getString("last_name_patient"));
            objPatient.setBirthDate(objResult.getDate("birth_date"));
            objPatient.setCardId(objResult.getString("document_identification"));

            patientByDocumentID.add(objPatient); // agregar paciente a la lista
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    } finally {
        configDB.closeConnection(); // cerrar conexión
    }

    return patientByDocumentID;
}
    public Object findById(int patientID) {

        Connection objConnection = configDB.openConnection();
        Patient objPatient = null;
        try {
            String sql = "SELECT * FROM patients WHERE patients.id_patient =?;"; //SQL sentence
            PreparedStatement objPrepare = objConnection.prepareStatement(sql); //prepareStatment
            objPrepare.setInt(1, patientID); //Preparestatment value
            ResultSet objResult = objPrepare.executeQuery(); //execute query

            while (objResult.next()) {

                objPatient = new Patient();
                objPatient.setId(objResult.getInt("id_patient"));
                objPatient.setName(objResult.getString("patient_name")); //values to  query parameters
                objPatient.setLastName(objResult.getString("last_name_patient"));//values to  query parameters
                objPatient.setBirthDate(objResult.getDate("birth_date"));
                objPatient.setCardId(objResult.getString("document_identification"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        configDB.closeConnection();

        return objPatient;
    }

}

