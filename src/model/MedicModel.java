package model;

import database.CRUD;
import database.configDB;
import entity.Medic;
import entity.Speciality;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class MedicModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = configDB.openConnection();

        Medic objMedic = (Medic) object;

        try {
            String sql = "INSERT INTO medics (medic_name,last_name_medic,id_spec) VALUES (?,?,?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objMedic.getName());
            objPrepare.setString(2, objMedic.getLastName());
            objPrepare.setInt(3, objMedic.getIdSpeciality());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()) {
                objMedic.setId(objResult.getInt(1));
            }
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Medic has been created successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding the medic: " + e.getMessage());
        }

        configDB.closeConnection();
        return objMedic;
    }

    @Override
    public boolean delete(Object object) {
        Medic objMedic = (Medic) object;
        boolean isDeleted = false;

        Connection objConnection = configDB.openConnection();

        try {
            String sql = "DELETE FROM medics WHERE id_medic=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objMedic.getId());

            int totalAffectedRows = objPrepare.executeUpdate();
            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Medic deleted successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting the medic: " + e.getMessage());
        }

        configDB.closeConnection();
        return isDeleted;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = configDB.openConnection();
        Medic objMedic = (Medic) object;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE medics SET medic_name=?, last_name_medic=?, id_spec=? WHERE id_medic=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, objMedic.getName());
            objPrepare.setString(2, objMedic.getLastName());
            objPrepare.setInt(3, objMedic.getIdSpeciality());
            objPrepare.setInt(4, objMedic.getId());

            int rowAffected = objPrepare.executeUpdate();
            if (rowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Medic updated successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating the medic: " + e.getMessage());
        }

        configDB.closeConnection();
        return isUpdated;
    }

    @Override
    public List<Object> findAll() {
        List<Object> listMedics = new ArrayList<>();
        Connection objConnection = configDB.openConnection();

        try {
            String sql = "SELECT * FROM medics INNER JOIN specialities ON specialities.id_speciality = medics.id_spec ORDER BY medics.id_medic ASC";
            PreparedStatement objPrepareStatement = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepareStatement.executeQuery();

            while (objResult.next()) {
                Medic objMedic = new Medic();
                Speciality objSpeciality = new Speciality();

                objMedic.setId(objResult.getInt("id_medic"));
                objMedic.setName(objResult.getString("medic_name"));
                objMedic.setLastName(objResult.getString("last_name_medic"));
                objMedic.setIdSpeciality(objResult.getInt("id_spec"));

                objSpeciality.setName(objResult.getString("speciality_name"));
                objSpeciality.setDescription(objResult.getString("speciality_description"));

                objMedic.setObjSpeciality(objSpeciality);
                listMedics.add(objMedic);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error: " + e.getMessage());
        }

        configDB.closeConnection();
        return listMedics;
    }

    public Object findById(int id) {
        Connection objConnection = configDB.openConnection();
        Medic objMedic = null;

        try {
            String sql = "SELECT * FROM medics WHERE id_medic=?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objMedic = new Medic();
                objMedic.setId(objResult.getInt("id_medic"));
                objMedic.setName(objResult.getString("medic_name"));
                objMedic.setLastName(objResult.getString("last_name_medic"));
                objMedic.setIdSpeciality(objResult.getInt("id_spec"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error finding medic by ID: " + e.getMessage());
        }

        configDB.closeConnection();
        return objMedic;
    }

    public static List<Medic> findBySpeciality(int specialityId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Medic> medics = new ArrayList<>();

        try {
            // open connection
            connection = configDB.openConnection();

            // Consult SQL
            String sql = "SELECT * FROM medics WHERE medics.id_spec = ?";

            // Prepare Statement
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, specialityId);

            // execute the query
            resultSet = preparedStatement.executeQuery();

            // mira resultados y agrega elementos a Medic
            while (resultSet.next()) {
                Medic medic = new Medic();
                medic.setId(resultSet.getInt("id_medic"));
                medic.setName(resultSet.getString("medic_name"));
                medic.setLastName(resultSet.getString("last_name_medic"));
                medic.setIdSpeciality(resultSet.getInt("id_spec"));

                //agrega los datos a medic
                medics.add(medic);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar médicos por especialidad: " + e.getMessage());
        } finally {
            //close connection
            configDB.closeConnection();
        }

        return medics;
    }
}
