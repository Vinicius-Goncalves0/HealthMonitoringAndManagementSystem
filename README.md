# HealthMonitoringAndManagementSystem
 Sistema de software que tem como objetivo monitorar remotamente a saúde de pacientes por meio da coleta de dados vitais a partir de dispositivos conectados, com a capacidade de analisar esses dados em tempo real para gerar diagnósticos e alertas automáticos.

 As siglas -> AM (Acesso para médicos) e AP (Acesso para paciente)


---- código para query ----
---- para filtrar o (nome) patient e o (dados do medicamento) medication

// sem id
SELECT 
    p.name AS patient_name,
    m.name AS medication_name,
    m.dosage,
    m.frequency,
    m.description,
    m.doctor,
    m.prescription_date
FROM 
    hospital_system.patients p
JOIN 
    hospital_system.patient_medications pm ON p.id = pm.patient_id
JOIN 
    hospital_system.medications m ON pm.medication_id = m.id
LIMIT 0, 1000;

// com id
SELECT 
    p.id AS patient_id,
    p.name AS patient_name,
    m.name AS medication_name,
    m.dosage,
    m.frequency,
    m.description,
    m.doctor,
    m.prescription_date
FROM 
    hospital_system.patients p
JOIN 
    hospital_system.patient_medications pm ON p.id = pm.patient_id
JOIN 
    hospital_system.medications m ON pm.medication_id = m.id
LIMIT 0, 1000;

---- código query para deletar da tabela por id ----
DELETE FROM hospital_system.patients WHERE id = 19;
SELECT * FROM hospital_system.patients;





implementar função de deletar médico e paciente dentro de cada um, como uma função de deletar sua própria conta