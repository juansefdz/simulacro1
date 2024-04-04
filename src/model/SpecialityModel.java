package model;
import database.CRUD;
import database.configDB;
import entity.Speciality;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import static java.sql.PreparedStatement.*;

public class SpecialityModel implements CRUD {

    @Override
    public Object insert(Object object) {

        Connection objConnection = configDB.openConnection(); //open connection

        Speciality objEspeciality = (Speciality) object;

        try {
            String sql = "INSERT INTO specialities (speciality_name, speciality_description) VALUES (?,?);"; //SQL sentence

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, RETURN_GENERATED_KEYS)
            ;
            objPrepare.setString(1, objEspeciality.getName()); //values to  query parameters
            objPrepare.setString(2, objEspeciality.getDescription());//values to  query parameters

            objPrepare.execute(); //execute the query
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objEspeciality.setId(objResult.getInt(1));
            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "speciality has been create successful");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error adding speciality" + e.getMessage());
        }

        configDB.closeConnection(); //close connection
        return objEspeciality;


    }

    public boolean delete(Object object) {

        Speciality objSpeciality = (Speciality) object; //convert obj entity

        boolean deleteFlag = false;

        Connection objConnection = configDB.openConnection(); //open connection

        try {
            String sql = "DELETE FROM specialities WHERE id_speciality=?;"; //SQL sentence

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //prepare statement

            objPrepare.setInt(1, objSpeciality.getId()); //assign valor from SQL sentence

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

        Speciality objSpeciality = (Speciality) object; //convert obj entity

        boolean isUpdated = false;

        Connection objConnection = configDB.openConnection();
        try {
            String sql= "UPDATE FROM specialities SET speciality_name =?, speciality_description =?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objSpeciality.getName());
            objPrepare.setString(2,objSpeciality.getDescription());

            int totalRowAffected = objPrepare.executeUpdate();
            if(totalRowAffected>0){
                isUpdated=true;
                JOptionPane.showMessageDialog(null,"updated successfully");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error updating: " + e.getMessage());
        }

        configDB.closeConnection();
        return isUpdated;
    }


    @Override
    public List<Object> findAll() {

        List<Object> listSpecialities = new ArrayList<>();

        Connection objConnection = configDB.openConnection(); //open connection
        try {
            String sql = "SELECT * FROM specialities ORDER BY id_speciality ASC;"; //SQL sentence
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);//prepare statement
            ResultSet objResult = objPrepareStatement.executeQuery(); //Execute query or prepare

            while (objResult.next()) { //Get results
                Speciality objSpeciality = new Speciality();
                objSpeciality.setId(objResult.getInt("id_speciality"));
                objSpeciality.setName(objResult.getString("speciality_name")); //values to  query parameters
                objSpeciality.setDescription(objResult.getString("speciality_description"));//values to  query parameters

                listSpecialities.add(objSpeciality); //add elements to author list

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }
        configDB.closeConnection(); //close connection
        return listSpecialities;

    }

    public static SpecialityModel instanceModel(){
        return new SpecialityModel();
    }
    public Object findById(int id) {

        Connection objConnection = configDB.openConnection();
        Speciality objSpeciality = null;
        try {
            String sql = "SELECT * FROM specialities WHERE id_speciality =?;"; //SQL sentence
            PreparedStatement objPrepare = objConnection.prepareStatement(sql); //prepareStatment
            objPrepare.setInt(1, id); //Preparestatment value
            ResultSet objResult = objPrepare.executeQuery(); //execute query

            while (objResult.next()) {

                objSpeciality = new Speciality();
                objSpeciality.setId(objResult.getInt("id_speciality"));
                objSpeciality.setName(objResult.getString("speciality_name")); //values to  query parameters
                objSpeciality.setDescription(objResult.getString("speciality_description"));//values to  query parameters
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        configDB.closeConnection();

        return objSpeciality;
    }




}

