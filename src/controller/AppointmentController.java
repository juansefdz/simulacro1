package controller;

import entity.Appointment;
import entity.Medic;
import entity.Patient;
import model.AppointmentModel;
import utils.Utils;

import javax.swing.*;

public class AppointmentController {
    public static void insert() {
        String date = JOptionPane.showInputDialog(null, "ingrese la fecha de la cita YYYY-MM-DD");
        String time = JOptionPane.showInputDialog(null, "ingresa la hora de la cita HH:MM:SS");
        String motive = JOptionPane.showInputDialog(null, "ingresa el motivo de la cita");

        Object[] optionPatients = Utils.listToArray(PatientController.instanceModel().findAll());

        Object[] optionMedic = Utils.listToArray(MedicController.instanceModel().findAll());

        Patient patientSelected = (Patient)JOptionPane.showInputDialog(null, "seleccione el paciente", "",JOptionPane.QUESTION_MESSAGE,null,optionPatients,optionPatients[0]);

        Medic medicSelected = (Medic)JOptionPane.showInputDialog(null, "seleccione el Medico", "",JOptionPane.QUESTION_MESSAGE,null,optionMedic,optionMedic[0]);

        instanceModel().insert(new Appointment(date,time,motive,patientSelected.getId(),medicSelected.getId(),patientSelected,medicSelected)); //ojo




    }

    public static AppointmentModel instanceModel (){
        return new AppointmentModel();
    }
}
