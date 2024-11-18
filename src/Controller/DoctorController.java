package Controller;

import java.sql.SQLException;
import java.util.List;

import Controller.db_Connections.DoctorDAO;
import Model.Doctor;

public class DoctorController {

    private DoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAO();
    }

    // Add a new docto
    public void addDoctor(Doctor doctor) throws SQLException {
        doctorDAO.addDoctor(doctor);
    }

    // Find a doctor by their name
    public Doctor findDoctorByName(String name) throws SQLException {
        return doctorDAO.findDoctorByName(name);
    }

    // Update a doctor's information
    public void updateDoctor(Doctor doctor) throws SQLException {
        doctorDAO.updateDoctor(doctor);
    }

    // List doctors by name
    public List<Doctor> listDoctorsByName(String name) throws SQLException {
        return doctorDAO.listDoctorsByName(name);
    }

}
