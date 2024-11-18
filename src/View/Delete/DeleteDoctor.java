package View.Delete;

import java.sql.SQLException;
import Controller.db_Connections.DoctorDAO;

public class DeleteDoctor {
    public void deleteDoctor(int doctorId) {
        DoctorDAO doctorDAO = new DoctorDAO();

        try {
            doctorDAO.deleteDoctor(doctorId);
            System.out.println("Doctor deleted successfully.");
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting doctor: " + e.getMessage() + " ---\n");
        }
    }
}