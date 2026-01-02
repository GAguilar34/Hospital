package tech.hospital.patient.dto;

import java.time.LocalDateTime;
import tech.hospital.patient.model.Patient;

public class PatientDTO {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private String genero;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaRegistro;

    public PatientDTO(int id, String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    //Convertimos a Patient en un Data Transfer Object para que el frontend pueda acceder a la informacion que requiera
    public static PatientDTO fromPatient(Patient patient){
        return new PatientDTO(
                patient.getId(),
                patient.getNombre(),
                patient.getApellido(),
                patient.getEmail(),
                patient.getEdad(),
                patient.getGenero(),
                patient.getTelefono(),
                patient.getDireccion(),
                patient.getFechaRegistro()
        );
    }

    //getters
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getEmail() {return email;}
    public int getEdad() {return edad;}
    public String getGenero() {return genero;}
    public String getTelefono() {return telefono;}
    public String getDireccion() {return direccion;}
    public LocalDateTime getFechaRegistro() {return fechaRegistro;}
}
