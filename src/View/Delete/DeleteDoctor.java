package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.db_Connections.DoctorDAO;

public class DeleteDoctor {
    public void deleteDoctor(int doctorId) {
        Scanner scan = new Scanner(System.in);
        DoctorDAO doctorDAO = new DoctorDAO();

        System.out.println("You are about to delete a doctor from the database. Are you sure you want to proceed? (yes/no)");
        System.out.println("Type 'Yes, I want to delete.' to confirm, or any other key to cancel.");
        String confirmation = scan.nextLine();

        if (!confirmation.equals("Yes, I want to delete.")) {
            try {
                doctorDAO.deleteDoctor(doctorId);
                System.out.println("Doctor deleted successfully.");
            } catch (SQLException e) {
                System.out.println("\n--- Error deleting doctor: " + e.getMessage() + " ---\n");
            }
        }
        scan.close();
    }
}