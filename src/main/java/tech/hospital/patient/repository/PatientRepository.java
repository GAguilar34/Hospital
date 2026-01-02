package tech.hospital.patient.repository;

import tech.hospital.patient.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Optional<Patient> findById(int id); //Obtenemos un paciente por su id
    List<Patient> findAll(); //Obtenemos todos los pacientes
    Optional<Patient> findByName(String name); //Obtenemos un paciente por su nombre
    Patient save(Patient patient); //Guardamos un nuevo paciente
    Patient update(Patient patient); //Actualizamos los datos de un paciente
    Boolean deleteById(int id); //Eliminamos un paciente por su id
}
