package tech.hospital.patient.service;

import tech.hospital.patient.model.Patient;
import tech.hospital.patient.dto.PatientDTO;
import tech.hospital.patient.repository.PatientRepository;
import tech.hospital.patient.repository.PatientRepositoryImpl;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;

import java.util.Optional;
import java.util.List;

public class PatientService{

    PatientRepository patientRepository;

    public PatientService(){
        this.patientRepository = new PatientRepositoryImpl();

    }

    //Metodo para validar que un correo es valido
    public boolean isValidEmail(String email){
        if(email.isEmpty() || email == null){
            JOptionPane.showMessageDialog(null, "El correo no puede estar vacio");
        }

        //Validamos que tenga arroba, punto y sea mayor a 6
        boolean hasASteal = email.contains("@");
        boolean hasPoint = email.contains(".");
        boolean eldestAsix = email.length() > 6;

        //Verificamos que primero este el arroba antes del punto
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".");
        boolean correctOrder = atIndex > 0 &&  dotIndex > atIndex + 1 && dotIndex < email.length() -2;

        // No debe haber espacios
        boolean noSpaces = !email.contains(" ");

        // Solo debe haber un @
        boolean singleAt = email.indexOf("@") == email.lastIndexOf("@");

        // Valida que cumpla con todas las validaciones
        boolean isValid = hasASteal && hasPoint && eldestAsix && correctOrder && noSpaces && singleAt;

        //Si email no es valido mostramos un mensaje
        if(!isValid){
            System.out.println("Invalid Email.");
        }

        return isValid; //Retornamos el email si es valido
    }

    //Metodo para recibir los datos de un paciente y almacenarlos
    public PatientDTO save(int id, String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro){

        //Validamos el email del paciente
        if(!isValidEmail(email)){
            JOptionPane.showMessageDialog(null, "El email no es valido");
            throw new IllegalArgumentException("El email no es valido");
        }

        //Agregamos al paciente
        Patient patient = new Patient(
                0,
                nombre,
                apellido,
                email,
                edad,
                genero,
                telefono,
                direccion,
                fechaRegistro
        );

        //Enviamos el paciente para guardalo en la base de datos
        Patient patientSave = patientRepository.save(patient);

        //Convertimos al paciente a dto(data transfer object) y los retornamos
        return PatientDTO.fromPatient(patientSave);
    }

    //Metodo para buscar a un paciente por su id
    public PatientDTO findById(int id){
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(PatientDTO::fromPatient).orElse(null);
    }

    //Metodo para buscar un paciente por su nombre
    public PatientDTO findByName(String nombre){
        Optional<Patient> patient = patientRepository.findByName(nombre);
        return patient.map(PatientDTO::fromPatient).orElse(null);
    }

    //Metodo para obtener todos los pacientes
    public List<Patient> findAll(){
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    //Metodo para eliminar un paciente
    public boolean deleteById(int id){
        //Verificamos que exista el paciente antes de eliminarlo
        Optional<Patient> patient = patientRepository.findById(id);
        if(!patient.isPresent()){
            JOptionPane.showMessageDialog(null, "El paciente no existe");
            return false;
        }
        return patientRepository.deleteById(id);
    }

    //Metodo para actualizar los datos del paciente
    public PatientDTO update(int id,  String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro){
        //Verificamos que el paciente exista antes de actualizar sus datos
        Optional<Patient> patient = patientRepository.findById(id);

        if(!patient.isPresent()) JOptionPane.showMessageDialog(null, "El paciente no existe");

        //Actualizamos los datos del paciente
        Patient patient1 = new Patient(id, nombre, apellido, email, edad, genero, telefono, direccion, fechaRegistro);
        Patient updated = patientRepository.update(patient1); //Guardamos los nuevos datos
        return PatientDTO.fromPatient(updated); //Retornamos los nuevos datos
    }
}
