package View;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.AppointmentController;
import Controller.PatientController;
import Model.Appointment;
import Model.Patient;

public class CreateAppointmentMenu {
    Scanner scan = new Scanner(System.in);
    private AppointmentController appointmentController;
    private PatientController patientController;

    public CreateAppointmentMenu() {
        this.appointmentController = new AppointmentController();
        this.patientController = new PatientController();
    }

    public void createAppointmentMenu(String patientName) {
        System.out.println("\nEnter appointment data and time: ");
        String appointmentDataTime = scan.nextLine();
        System.out.println("Enter the appointment's doctor name: ");
        String appointmentDoctorName = scan.nextLine();
        System.out.println("Enter the diagnosis: ");
        String diagnosis = scan.nextLine();

        try {
            Patient patient = patientController.findPatientByName(patientName);

            if (patient != null) {
                Appointment appointment = new Appointment(appointmentDataTime, appointmentDoctorName, diagnosis);
                appointmentController.addAppointmentToPatient(appointment, patient);
                System.out.println("Appointment registered successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error registering appointment patient: " + e.getMessage());
        }
    }

}
