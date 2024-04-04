package controller;

import entity.Speciality;
import model.SpecialityModel;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static model.SpecialityModel.instanceModel;

public class SpecialityController {

    SpecialityModel objSpecialityModel;

    public SpecialityController(){
        this.objSpecialityModel = new SpecialityModel();
    }

    public void insert() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JTextField specName = new JTextField();
        JTextField specialityDescription = new JTextField();

        panel.add(new JLabel("Speciality: "));
        panel.add(specName);

        panel.add(new JLabel("Specialty description: "));
        panel.add(specialityDescription);

        int result = JOptionPane.showConfirmDialog(null, panel, "SPECIALTY MENU", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String specialityName = specName.getText();
            String specialityInfo = specialityDescription.getText();

            Speciality speciality = new Speciality(specialityName, specialityInfo);
            instanceModel().insert(speciality);

            JOptionPane.showMessageDialog(null, speciality.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos son necesarios");
        }
    }


    public void delete() {
        Object[] options= Utils.listToArray(instanceModel().findAll());

        int confirm = 1;
        Speciality objSpeciality = (Speciality) JOptionPane.showInputDialog(null, "Enter the ID of the specialization to delete", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        instanceModel().delete(objSpeciality);

    }

    public static void update (){
        Object[] options= Utils.listToArray(instanceModel().findAll());

        int confirm = 1;
        Speciality objSpeciality = (Speciality) JOptionPane.showInputDialog(null, "Enter the ID of the specialization to update", "", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        objSpeciality.setName(JOptionPane.showInputDialog(null, "enter the new name of the specialty",objSpeciality.getName()));
        objSpeciality.setDescription(JOptionPane.showInputDialog(null, "enter the new description of the specialty",objSpeciality.getDescription()));

        instanceModel().update(objSpeciality);

    }

    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null,list);


    }
    public static String getAll (List<Object>list){
        String listString ="REGISTER LIST: \n";

        for (Object temp: list){
            Speciality objSpeciality = (Speciality) temp;
            listString +=objSpeciality.toString()+"\n";
        }
        return listString;
    }


    public static SpecialityModel instanceModel(){
        return new SpecialityModel();
    }
}
