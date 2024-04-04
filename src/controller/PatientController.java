package controller;

import entity.Medic;
import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class PatientController {

    PatientModel objPatientModel;

    public PatientController() {
        this.objPatientModel = new PatientModel();
    }

    public static void getAll() {
        List<Object> allPatients = instanceModel().findAll();
        String list = getAll(allPatients);
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {
        StringBuilder listString = new StringBuilder("REGISTER LIST: \n");

        for (Object temp : list) {
            Patient objPatient = (Patient) temp;
            listString.append(objPatient.toString()).append("\n");
        }
        return listString.toString();
    }


    public void insert() throws ParseException {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JTextField patientName = new JTextField();
        JTextField patientLastName = new JTextField();
        JTextField patientBirthDate = new JTextField();
        JTextField patientIdCard = new JTextField();

        panel.add(new JLabel("Enter the patient name:"));
        panel.add(patientName);
        panel.add(new JLabel("Enter the patient's last name:"));
        panel.add(patientLastName);
        panel.add(new JLabel("Enter the birth date of the patient in format YYYY-MM-DD:"));
        panel.add(patientBirthDate);
        panel.add(new JLabel("Enter card ID of the patient:"));
        panel.add(patientIdCard);

        int result = JOptionPane.showConfirmDialog(null, panel, "PATIENT INFORMATION PANE: ", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String patientNameString = patientName.getText();
            String patientLastNameString = patientLastName.getText();
            LocalDate birthDateString = LocalDate.parse(patientBirthDate.getText());
            int patientIdCardString = Integer.parseInt(patientIdCard.getText());


            Patient objPatient = new Patient();
            objPatient.setName(patientNameString);
            objPatient.setLastName(patientLastNameString);
            objPatient.setBirthDate((Date.valueOf(birthDateString)));
            objPatient.setCardId(String.valueOf(patientIdCardString));

            objPatient = (Patient) instanceModel().insert(objPatient);
            JOptionPane.showMessageDialog(null, "Patient created successfully:\n" + objPatient.toString());

        }
    }


    public void findByDocumentID() {
        int documentID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Document ID of the patient you want to search"));

        // Get list of patients by ID
        List<Patient> patients = (List<Patient>) this.objPatientModel.findByDocumentID(documentID);


        if (patients.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No patients found with document ID: " + documentID);
        } else {
            // Mostrar información de cada paciente encontrado
            StringBuilder patientInfo = new StringBuilder("PATIENTS FOUND BY DOCUMENT ID => " + documentID + "\n");
            for (Patient patient : patients) {
                patientInfo.append(patient.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, patientInfo.toString());
        }
    }

    public void delete() {
        String listPatientString = getAll(objPatientModel.findAll());

        int confirm = 1;
        int documentIdDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatientString + "\nEnter the document ID of the patient to delete"));

        // Search patients by document ID
        List<Patient> patientsToDelete = (List<Patient>) this.objPatientModel.findByDocumentID(documentIdDelete);


        if (patientsToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No patient found with document ID: " + documentIdDelete);
        } else {

            Patient patientToDelete = patientsToDelete.get(0);


            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this patient?\n" + patientToDelete.toString());

            if (confirm == JOptionPane.OK_OPTION) {
                // Eliminar el paciente
                boolean deleted = this.objPatientModel.delete(patientToDelete);

                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Patient deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete patient.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operation cancelled.");
            }
        }
    }

    public void update() {
        String listPatients = this.getAll(this.objPatientModel.findAll());
        int idUpdate = Integer.parseInt((JOptionPane.showInputDialog(listPatients + "\nEnter the ID of the patient to edit")));

        List<Patient> patientsToUpdate = (List<Patient>) this.objPatientModel.findByDocumentID(idUpdate);

        // Verificar si se encontró al menos un paciente
        if (patientsToUpdate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No patient found with ID: " + idUpdate);
        } else {

            Patient objPatient = patientsToUpdate.get(0);

            JPanel panel = new JPanel(new GridLayout(2, 1));
            JTextField patientName = new JTextField(objPatient.getName());
            JTextField patientLastName = new JTextField(objPatient.getLastName());
            JTextField patientBirth = new JTextField(String.valueOf(objPatient.getBirthDate()));
            JTextField documentIdPatient = new JTextField(objPatient.getCardId());

            panel.add(new JLabel("Name: "));
            panel.add(patientName);
            panel.add(new JLabel("Last Name: "));
            panel.add(patientLastName);
            panel.add(new JLabel("Birth Date: "));
            panel.add(patientBirth);
            panel.add(new JLabel("Document ID: "));
            panel.add(documentIdPatient);

            int result = JOptionPane.showConfirmDialog(null, panel, "Enter patient details: \n", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String patientNameString = patientName.getText();
                String patientLastNameString = patientLastName.getText();
                LocalDate patientBirthDate = LocalDate.parse(patientBirth.getText());
                String documentIDPatientString = documentIdPatient.getText();


                objPatient.setName(patientNameString);
                objPatient.setLastName(patientLastNameString);
                objPatient.setBirthDate(Date.valueOf(patientBirthDate));
                objPatient.setCardId(documentIDPatientString);


                this.objPatientModel.update(objPatient);
                JOptionPane.showMessageDialog(null, "Patient details updated successfully.");
            }
        }
    }


    public void findById() {

        int patientID = Integer.parseInt(JOptionPane.showInputDialog(null, " insert the ID of the patient you want to search "));


        Object patId = this.objPatientModel.findById(patientID);
        Patient patientTemp = (Patient) patId;
        if (patId == null) {
            JOptionPane.showMessageDialog(null, "Patient ID not found");
        } else {


            StringBuilder showPatient = new StringBuilder("PATIENT FOUND BY ID => " + patientID + "\n");

            showPatient.append("ID: ").append(patientTemp.getId()).append("\n");
            showPatient.append("name: ").append(patientTemp.getName()).append("\n");
            showPatient.append("Last Name: ").append(patientTemp.getLastName()).append("\n\n");
            showPatient.append("Birth Date: ").append(patientTemp.getBirthDate()).append("\n");
            showPatient.append("Document ID: ").append(patientTemp.getCardId()).append("\n\n");

            JOptionPane.showMessageDialog(null, showPatient.toString());
        }
    }

    public static PatientModel instanceModel() {
        return new PatientModel();
    }
}



