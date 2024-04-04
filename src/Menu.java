import controller.MedicController;
import controller.PatientController;
import controller.SpecialityController;


import javax.swing.*;
import java.text.ParseException;

public class Menu {
    public void menu() throws ParseException {

        int mainOption = 0;
        SpecialityController objSpecialityController = new SpecialityController();
        PatientController objPatientController = new PatientController();
        MedicController objMedicController = new MedicController();
        SpecialityController specialityController = new SpecialityController();




        String[] mainOptions = {"SPECIALITIES SECTION", "MEDIC SECTION", "PATIENTS SECTION", "APPOINTMENTS", "EXIT"};
        do {

            mainOption = JOptionPane.showOptionDialog(null, "Choose one option: ", "HOSPITAL SYSTEM MENU", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, mainOptions, mainOptions[0]);

            switch (mainOption) {
                case 0:
                    String optionSpeciality;
                    do {
                        String[] optionsMenu = {"Create Specialty", "Delete Specialty", "show all specialities","update speciality", "Back"};
                        optionSpeciality = (String) JOptionPane.showInputDialog(null, "Select an option: ", "Specialties Section", JOptionPane.QUESTION_MESSAGE, null, optionsMenu, optionsMenu[0]);

                        if (optionSpeciality != null) {
                            switch (optionSpeciality) {
                                case "Create Specialty":
                                    specialityController.insert();
                                    break;
                                case "Delete Specialty":
                                    specialityController.delete();
                                    break;
                                case "show all specialities":
                                    SpecialityController.getAll();
                                    break;
                                case "update speciality":
                                    SpecialityController.update();
                                case "Back":
                                    break;
                            }
                        }
                    } while (!"Back".equals(optionSpeciality));

                    break;
                case 1:
                    String optionMedic;
                    do {
                        String[] optionsMenu = {"Add New Medic", "Search medic by speciality", "update speciality by medic","delete medic", "Back"};
                        optionMedic = (String) JOptionPane.showInputDialog(null, "Select an option: ", "Medics section", JOptionPane.QUESTION_MESSAGE, null, optionsMenu, optionsMenu[0]);

                        if (optionMedic != null) {
                            switch (optionMedic) {
                                case "Add New Medic":
                                    objMedicController.insert();
                                    break;
                                case "Search medic by speciality":
                                    objMedicController.findBySpeciality();
                                    break;
                                case "update speciality by medic":
                                    objMedicController.update();
                                    break;
                                case "delete medic":
                                    objMedicController.delete();
                                    break;
                                case "Back":
                                    break;
                            }
                        }
                    } while (!"Back".equals(optionMedic));
                    break;
                case 2:
                    String optionPatients;
                    do {
                        String[] optionsMenu = {"Enter a new patient", "Delete a specific patient", "Search for a specific patient by Document ID", "Show all patients", "Edit patient information", "Back"};
                        optionPatients = (String) JOptionPane.showInputDialog(null, "Select an option: ", "Specialties Section", JOptionPane.QUESTION_MESSAGE, null, optionsMenu, optionsMenu[0]);

                        if (optionPatients != null) {
                            switch (optionPatients) {
                                case "Enter a new patient":
                                    objPatientController.insert();
                                    break;
                                case "Delete a specific patient":
                                    objPatientController.delete();
                                    break;
                                case "Search for a specific patient by Document ID":
                                    objPatientController.findByDocumentID();
                                    break;
                                case "Show all patients":
                                    objPatientController.getAll();
                                    break;
                                case "Edit patient information":
                                    objPatientController.update();
                                    break;
                                case "Back":
                                    break;
                            }
                        }
                    } while (!"Back".equals(optionPatients));
                    break;
                case 3:
                    String optionAppointment;
                    do {
                        String[] optionsMenu = {"List appointment", "Create New appointments", "Delete appointment", "Update appointment", "Back"};
                        optionAppointment = (String) JOptionPane.showInputDialog(null, "Select an option: ", "Specialties Section", JOptionPane.QUESTION_MESSAGE, null, optionsMenu, optionsMenu[0]);

                        if (optionAppointment != null) {
                            switch (optionAppointment) {
                                case "List appointments":

                                    break;
                                case "Create New appointments":

                                    break;
                                case "Delete appointment":

                                    break;
                                case "Update appointment":

                                    break;
                                case "Back":
                                    break;
                            }
                        }
                    } while (!"Back".equals(optionAppointment));
                    break;
                case 4:
                    System.out.println("bye!");
                    break;
            }

        } while (mainOption != 4);


    }
}
