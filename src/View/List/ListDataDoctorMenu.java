package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import Controller.DoctorController;
import Model.Doctor;

public class ListDataDoctorMenu {
    Scanner scan = new Scanner(System.in);

    public void displayDoctors(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found with the given name.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println("ID: " + doctor.getId());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Specialty: " + doctor.getSpecialty());
                System.out.println("CRM: " + doctor.getCrm());
                System.out.println("Email: " + doctor.getEmail());
                System.out.println("Phone: " + doctor.getPhone());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listDoctorsByName(String doctorName) {
        DoctorController doctorController = new DoctorController();
        ListDataDoctorMenu listDataDoctorMenu = new ListDataDoctorMenu();


        try {
            List<Doctor> doctors = doctorController.listDoctorsByName(doctorName);
            listDataDoctorMenu.displayDoctors(doctors);
            System.out.println("\n Press enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing doctor: " + e.getMessage() + " ---\n");
        }
    }
    

}
