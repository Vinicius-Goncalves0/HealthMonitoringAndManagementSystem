package View.Delete;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.db_Connections.DoctorDAO;

public class DeleteDoctor {
    @SuppressWarnings("resource")
    public void deleteDoctor(int doctorId) {
        Scanner scan = new Scanner(System.in);
        DoctorDAO doctorDAO = new DoctorDAO();

        try {
            doctorDAO.deleteDoctor(doctorId);
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting doctor: " + e.getMessage() + " ---\n");
        }
    }
}