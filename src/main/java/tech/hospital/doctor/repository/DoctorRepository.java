package tech.hospital.doctor.repository;

import tech.hospital.doctor.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    Optional<Doctor> findById(int id); //Buscamos un doctor mediante su id
    List<Doctor> findAll(); //Obtenemos todos los doctores registrados
    Doctor save(Doctor doctor); //Agregamos un nuevo doctor
    Doctor update(Doctor doctor); //Actualizamos los datos de un doctor
    boolean deleteById(int id); //Eliminamos un docotr mediante su id
}
