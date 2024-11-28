package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.PatientDAO;
import Model.Patient;

public class CreatePatientMenu {
    Scanner scan = new Scanner(System.in);

    public void createPatientMenu() {
        System.out.print("\n=== Create Patient ===\n");
        System.out.println("Patient's name: ");
        String name = scan.nextLine();
        System.out.println("Patient's CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Patient's birth date: ");
        String birthDate = scan.nextLine();
        System.out.println("Patient's medical history: ");
        String histories = scan.nextLine();
        System.out.println("Patient's address: ");
        String address = scan.nextLine();
        System.out.println("Patient's phone: ");
        String phone = scan.nextLine();
        System.out.println("Patient's email: ");
        String email = scan.nextLine();

        Patient patient = new Patient(name, cpf, birthDate, address, phone, email, histories);

        PatientDAO patientDAO = new PatientDAO();
        try {
            patientDAO.addPatient(patient);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
