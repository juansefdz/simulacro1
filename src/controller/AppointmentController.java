package controller;

import entity.Appointment;
import entity.Medic;
import entity.Patient;
import model.AppointmentModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class AppointmentController {
    public static void insert() {
        String date = JOptionPane.showInputDialog(null, "Ingresa la fecha de la cita AAAA-MM-DD");
        String time = JOptionPane.showInputDialog(null, "Ingresa la hora de la cita HH:MM:SS");
        String motive = JOptionPane.showInputDialog(null, "Ingresa el motivo de la cita");


        Object[] optionPatients = Utils.listToArray(PatientController.instanceModel().findAll());

        Object[] optionMedic = Utils.listToArray(MedicController.instanceModel().findAll());

        Patient patientSelected = (Patient)JOptionPane.showInputDialog(null, "seleccione el paciente", "",JOptionPane.QUESTION_MESSAGE,null,optionPatients,optionPatients[0]);

        Medic medicSelected = (Medic)JOptionPane.showInputDialog(null, "seleccione el Medico", "",JOptionPane.QUESTION_MESSAGE,null,optionMedic,optionMedic[0]);

        instanceModel().insert(new Appointment()); //ojo




    }
    public static void getAll(){
        String listString = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,listString);

    }
    public static String getAll(List<Object> list){
        String listString ="lista de registros \n";
        for (Object temp: list){
            Appointment obj= (Appointment) temp;
            listString +=obj.toString()+"\n";
        }
        return listString;

    }

    public static AppointmentModel instanceModel (){
        return new AppointmentModel();
    }

    public static void delete (){
        Object[] options= Utils.listToArray(instanceModel().findAll());

        Appointment appointmentSelected = (Appointment) JOptionPane.showInputDialog(null, "seleccione la cita a eliminar", "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        instanceModel().delete(appointmentSelected);

    }

    public static void update (){
        Object[] options= Utils.listToArray(instanceModel().findAll());

        Appointment appointmentSelected = (Appointment) JOptionPane.showInputDialog(null, "seleccione la cita a eliminar", "",JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        instanceModel().delete(appointmentSelected);

        appointmentSelected.setAppointmentTime(JOptionPane.showInputDialog(null, "ingrese la nueva fecha de la cita YYYY-MM-DD",appointmentSelected.getAppointmentTime()));
        appointmentSelected.setAppointmentHour( JOptionPane.showInputDialog(null, "ingresa la nueva hora de la cita HH:MM:SS",appointmentSelected.getAppointmentHour()));
        appointmentSelected.setMotive(JOptionPane.showInputDialog(null, "ingresa el nuevo motivo de la cita",appointmentSelected.getMotive()));


        Object[] optionPatients = Utils.listToArray(PatientController.instanceModel().findAll());

        Object[] optionMedic = Utils.listToArray(MedicController.instanceModel().findAll());

        appointmentSelected.setObjPatient( (Patient)JOptionPane.showInputDialog(null, "seleccione el paciente", "",JOptionPane.QUESTION_MESSAGE,null,optionPatients,optionPatients[0]));

        appointmentSelected.setIdPatient(appointmentSelected.getObjPatient().getId());


        appointmentSelected.setObjMedic((Medic)JOptionPane.showInputDialog(null, "seleccione el Medico", "",JOptionPane.QUESTION_MESSAGE,null,optionMedic,optionMedic[0]));

        appointmentSelected.setIdMedic(appointmentSelected.getObjMedic().getId());

        instanceModel().update(appointmentSelected);
    }
}
