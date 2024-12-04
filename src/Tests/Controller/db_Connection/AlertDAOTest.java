package Tests.Controller.db_Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Controller.db_Connections.AlertDAO;
import Model.Alert;
import Model.Device;
import Model.Patient;

public class AlertDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private AlertDAO alertDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        alertDAO = new AlertDAO();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    public void testGenerateAlert() throws SQLException {
        Alert alert = new Alert();
        alert.setType("type");
        alert.setMessage("message");
        alert.setDoctor("doctor");
        alert.setDate("date");

        Device device = new Device();
        device.setId(1);

        Patient patient = new Patient();
        patient.setId(1);

        alertDAO.generateAlert(alert, device, patient);

        verify(mockPreparedStatement, times(3)).executeUpdate();
    }

    @Test
    public void testIsAlertOwnedByPatient() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("count")).thenReturn(1);

        boolean result = alertDAO.isAlertOwnedByPatient("patientName", 1);

        assertTrue(result);
    }

    @Test
    public void testDeletePatientAlert() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);

        alertDAO.deletePatientAlert("patientName", 1);

        verify(mockPreparedStatement, times(3)).executeUpdate();
    }

    @Test
    public void testDeleteAlertById() throws SQLException {
        alertDAO.deleteAlertById(1);

        verify(mockPreparedStatement, times(3)).executeUpdate();
    }

    @Test
    public void testListAllAlerts() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("type")).thenReturn("type");
        when(mockResultSet.getString("message")).thenReturn("message");
        when(mockResultSet.getString("doctor")).thenReturn("doctor");
        when(mockResultSet.getString("data")).thenReturn("data");

        List<Alert> alerts = alertDAO.listAllAlerts();

        assertEquals(1, alerts.size());
    }

    @Test
    public void testGetPatientIdByAlertId() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("patient_id")).thenReturn(1);

        int patientId = alertDAO.getPatientIdByAlertId(1);

        assertEquals(1, patientId);
    }

    @Test
    public void testListAlertsByPatientId() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("type")).thenReturn("type");
        when(mockResultSet.getString("message")).thenReturn("message");
        when(mockResultSet.getString("doctor")).thenReturn("doctor");
        when(mockResultSet.getString("data")).thenReturn("data");

        List<Alert> alerts = alertDAO.listAlertsByPatientId(1);

        assertEquals(1, alerts.size());
    }

    @Test
    public void testAlertExists() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        boolean exists = alertDAO.alertExists("message", "type");

        assertTrue(exists);
    }
}
