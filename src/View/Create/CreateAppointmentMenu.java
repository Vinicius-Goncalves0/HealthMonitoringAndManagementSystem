package View.Create;

import java.sql.SQLException;
import java.util.Scanner;
import Controller.AppointmentController;
import Controller.PatientController;
import Controller.db_Connections.DoctorDAO;
import Model.Doctor;
import Model.Appointment;
import Model.Patient;

public class CreateAppointmentMenu {
    Scanner scan = new Scanner(System.in);
    private AppointmentController appointmentController;
    private PatientController patientController;
    CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
    DoctorDAO doctorDAO = new DoctorDAO();

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
            Doctor doctor = doctorDAO.findDoctorByName(appointmentDoctorName);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
        
                    if (patient != null) {
                        Appointment appointment = new Appointment(appointmentDataTime, appointmentDoctorName, diagnosis);
                        appointmentController.addAppointmentToPatient(appointment, patient);
                        System.out.println("\nAppointment registered successfully.");
                        
                        System.out.println("Do you want to add a medical prescription? (Y/N)");
                        String answer = scan.nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            System.out.println("\nAdding medication...");
                            createMedicationMenu.createMedicationMenuForAppointment(patientName, appointment.getId());
                        } else {
                            System.out.println("Returning to main menu...");
                        }
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error registering appointment patient: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + appointmentDoctorName + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + appointmentDoctorName + " " + e.getMessage()
                    + " ---\n");
        }
    }

}
