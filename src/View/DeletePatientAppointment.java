package View;

import java.sql.SQLException;
import java.util.Scanner;

import Controller.AppointmentController;

public class DeletePatientAppointment {
    @SuppressWarnings("resource")
    public void deleteAppointment(String patientName) {
        Scanner scan = new Scanner(System.in);
        AppointmentController appointmentController = new AppointmentController();

        System.out.println("Appointment ID:");
        int appointmentId = scan.nextInt();

        try {
            appointmentController.deletePatientAppointmentByName(patientName, appointmentId);
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting appointment: " + e.getMessage() + " ---\n");
        }
    }
}
