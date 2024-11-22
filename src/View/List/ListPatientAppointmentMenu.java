package View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Controller.AppointmentController;
import Model.Appointment;

public class ListPatientAppointmentMenu {
    Scanner scan = new Scanner(System.in);

    public void displayShortAppointments(List<Appointment> appointments) {

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for the given patient.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("\nAppointment ID: " + appointment.getId());
                System.out.println("Appointment Data and Time: " + appointment.getAppointmentDateTime());
                System.out.println("Doctor responsible: " + appointment.getDoctor());
                System.out.println("-----------------------------");
            }
        }
    }

    public void listShortAppointmentsByPatientName(String patientName) {
        ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();
        AppointmentController appointmentController = new AppointmentController();

        try {
            List<Appointment> appointments = appointmentController.listAppointmentsByPatientName(patientName);
            listPatientAppointmentMenu.displayShortAppointments(appointments);
        } catch (SQLException e) {
            System.out.println("\n--- Error when listing appointments: " + e.getMessage() + " ---\n");
        }
    }

    // _____________________________________________________________

    public void displayAppointments(List<Appointment> appointments) {
        ListDataDoctor listDataDoctorMenu = new ListDataDoctor();
        ListPatientMedicationMenu listMedicationsByAppointmentID = new ListPatientMedicationMenu();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found for the given patient.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("\nAppointment ID: " + appointment.getId());
                System.out.println("Appointment Data and Time: " + appointment.getAppointmentDateTime());
                System.out.println("Doctor responsible: " + appointment.getDoctor());
                System.out.println("Diagnosis: " + appointment.getDiagnosis());
                System.out.println("-----------------------------");

                System.out.println("\n Would like to see the doctor's prescription for this consultation? (Y/N)");
                String choiceP = scan.nextLine();
                if (choiceP.equalsIgnoreCase("Y")) {
                    listMedicationsByAppointmentID.listMedicationsByAppointmentID(appointment.getId());
                    
                }

                System.out.println("\n Would you like to see the doctor in charge? (Y/N)");
                String choiceD = scan.nextLine();
                if (choiceD.equalsIgnoreCase("Y")) {
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
