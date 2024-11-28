package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.DoctorDAO;
import Model.Doctor;

public class CreateDoctorMenu {
    Scanner scan = new Scanner(System.in);

    public void createDoctorMenu() {
        System.out.print("\n=== Create Doctor ===\n");
        System.out.println("|| Doctor's name: ");
        String name = scan.nextLine();
        System.out.println("|| Doctor's specialty: ");
        String specialty = scan.nextLine();
        System.out.println("|| Doctor's CRM: ");
        String crm = scan.nextLine();
        System.out.println("|| Doctor's phone: ");
        String phone = scan.nextLine();
        System.out.println("|| Doctor's email: ");
        String email = scan.nextLine();

        Doctor doctor = new Doctor(name, specialty, crm, phone, email);

        DoctorDAO doctorDAO = new DoctorDAO();
        try {
            doctorDAO.addDoctor(doctor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
