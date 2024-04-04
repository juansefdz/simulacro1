package controller;

import entity.Medic;
import entity.Speciality;
import model.MedicModel;
import model.SpecialityModel;
import utils.Utils;

import javax.swing.*;
import java.util.List;

public class MedicController{


    public static void getAll() {
        List<Object> allMedics = instanceModel().findAll();
        String list = getAll(allMedics);
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list) {
        StringBuilder listString = new StringBuilder("REGISTER LIST: \n");

        for (Object temp : list) {
            Medic objMedic = (Medic) temp;
            listString.append(objMedic.toString()).append("\n");
        }
        return listString.toString();
    }

    public void insert() {
        Object[] optionsMedics = Utils.listToArray(SpecialityController.instanceModel().findAll());


        if (optionsMedics.length>0) {
            String[] specialityOptions = new String[optionsMedics.length];
            for (int i = 0; i < optionsMedics.length; i++) {
                specialityOptions[i] = ((Speciality) optionsMedics[i]).getName();
            }

            String selectedSpecialityName = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a specialty for the medic:",
                    "SPECIALITIES OPTIONS",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    specialityOptions,
                    specialityOptions[0]
            );

            Speciality selectedSpeciality = null;
            for (Object tempSpeciality : optionsMedics) {
                Speciality speciality = (Speciality) tempSpeciality;
                if (speciality.getName().equals(selectedSpecialityName)) {
                    selectedSpeciality = speciality;
                    break;
                }
            }

            if (selectedSpeciality != null) {
                String nameMedic = JOptionPane.showInputDialog(null, "Enter medic name: ");
                String lastNameMedic = JOptionPane.showInputDialog(null, "Enter medic last name: ");

                Medic newMedic = new Medic();
                newMedic.setName(nameMedic);
                newMedic.setLastName(lastNameMedic);
                newMedic.setIdSpeciality(selectedSpeciality.getId());
                newMedic.setObjSpeciality(selectedSpeciality);
                instanceModel().insert(newMedic);

                JOptionPane.showMessageDialog(null, "Medic added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Selected specialty not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There are no specialties available.");
        }
    }

    public void delete() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Medic objMedic = (Medic) JOptionPane.showInputDialog(
                null,
                "Enter the ID of the medic to delete",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objMedic);
    }

    public static void update() {
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Medic objMedic = (Medic) JOptionPane.showInputDialog(
                null,
                "Select the medic to update",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (objMedic != null) {
            String newName = JOptionPane.showInputDialog(null, "Enter the new name of the medic", objMedic.getName());
            String newLastName = JOptionPane.showInputDialog(null, "Enter the new last name", objMedic.getLastName());

            Object[] MedicsOptions = Utils.listToArray(SpecialityController.instanceModel().findAll());


            Speciality selectedSpeciality = (Speciality) JOptionPane.showInputDialog(
                    null,
                    "Select the new specialty for the medic:",
                    "SPECIALITIES OPTIONS",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    MedicsOptions,
                    MedicsOptions[0]
            );

            if (selectedSpeciality != null) {
                objMedic.setName(newName);
                objMedic.setLastName(newLastName);
                objMedic.setIdSpeciality(selectedSpeciality.getId());

                boolean updated = instanceModel().update(objMedic);
                if (updated) {
                    JOptionPane.showMessageDialog(null, "Medic updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update medic.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selected specialty not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No medic selected.");
        }
    }

    public void findBySpeciality() {
        // get all available specialties
        Object[] options = Utils.listToArray(instanceModel().findAll());

        if (options.length > 0) {
            // show all specialities
            StringBuilder optionBuilder = new StringBuilder("Select a speciality:\n");
            for (Object option : options) {
                System.out.println(option.getClass().getName());
                Speciality speciality = (Speciality) option;
                optionBuilder.append(speciality.getId()).append(". ").append(speciality.getName()).append("\n");
            }
            int selectedSpecialityId = Integer.parseInt(JOptionPane.showInputDialog(null, optionBuilder.toString()));

            // Search for medics by selected specialty
            List<Medic> medics = MedicModel.findBySpeciality(selectedSpecialityId);

            // show medics
            StringBuilder result = new StringBuilder("Medics found for selected speciality: \n");
            for (Medic medic : medics) {
                result.append(medic.getName()).append(" ").append(medic.getLastName()).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "There are no specialities available.");
        }
    }


    public static MedicModel instanceModel() {
        return new MedicModel();
    }

}
