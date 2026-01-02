package tech.hospital.patient.controller;

import tech.hospital.patient.model.Patient;
import tech.hospital.patient.dto.PatientDTO;
import tech.hospital.patient.service.PatientService;

import java.time.LocalDateTime;

public class PatientController {
    PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Metodo para guardar un nuevo paciente
    public PatientDTO save(int id, String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro){
        return patientService.save(id, nombre, apellido, email, edad, genero, telefono, direccion, fechaRegistro);
    }

    //Metodo para buscar un paciente por su id
    public PatientDTO findById(int id){
        return patientService.findById(id);
    }

    //Metodo para buscar un paciente por su nombre
    public PatientDTO findByName(String name){
        return patientService.findByName(name);
    }

    //Metodo para mostar toda la lista de pacientes
    public PatientDTO findAll(){
        return (PatientDTO) patientService.findAll();
    }

    //Metodo para actualizar los datos de un paciente
    public PatientDTO update(int id, String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro){
        return patientService.update(id, nombre, apellido, email, edad, genero, telefono, direccion, fechaRegistro);
    }

    //Metodo para eliminar un paciente por su id
    public boolean deleteById(int id){
        return patientService.deleteById(id);
    }

}