package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.DoctorDAO;
import Model.Doctor;

public class CreateDoctorMenu {
    Scanner scan = new Scanner(System.in);

    public void createDoctorMenu() {
        System.out.println("\nEnter doctor's name: ");
        String name = scan.nextLine();
        System.out.println("Enter doctor's specialty: ");
        String specialty = scan.nextLine();
        System.out.println("Enter doctor's CRM: ");
        String crm = scan.nextLine();
        System.out.println("Enter doctor's phone: ");
        String phone = scan.nextLine();
        System.out.println("Enter doctor's email: ");
        String email = scan.nextLine();

        // Create a new Doctor object
        Doctor doctor = new Doctor(name, specialty, crm, phone, email);

        // Add the doctor to the database
        DoctorDAO doctorDAO = new DoctorDAO();
        try {
            doctorDAO.addDoctor(doctor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
