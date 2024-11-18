package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.AppointmentController;
import Model.Appointment;

public class ListPatientAppointmentMenu {
    Scanner scan = new Scanner(System.in);

    public void displayAppointments(List<Appointment> appointments) {
        ListDataDoctorMenu listDataDoctorMenu = new ListDataDoctorMenu();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for the given patient.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("\nAppointment ID: " + appointment.getId());
                System.out.println("Appointment Data and Time: " + appointment.getAppointmentDateTime());
                System.out.println("Doctor responsible: " + appointment.getDoctor());
                System.out.println("Diagnosis: " + appointment.getDiagnosis());
                System.out.println("-----------------------------");
                System.out.println("\n Would you like to see the doctor in charge? (Y/N)");
                String choice = scan.nextLine();

                if (choice.equalsIgnoreCase("Y")) {
                    listDataDoctorMenu.listDoctorsByName(appointment.getDoctor());
                }
            }
        }
    }

    public void listAppointmentsByPatientName(String patientName) {
        ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();
        AppointmentController appointmentController = new AppointmentController();

        try {
            List<Appointment> appointments = appointmentController.listAppointmentsByPatientName(patientName);
            listPatientAppointmentMenu.displayAppointments(appointments);
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing appointments: " + e.getMessage() + " ---\n");
        }
    }
}
