package tech.hospital.doctor.dto;

import tech.hospital.doctor.model.Doctor;

public class DoctorDTO {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;

    public DoctorDTO(int id,  String nombre, String apellido, String email, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public static DoctorDTO fromDoctor(Doctor doctor){
        return new DoctorDTO(
                doctor.getId(),
                doctor.getNombre(),
                doctor.getApellido(),
                doctor.getEmail(),
                doctor.getTelefono(),
                doctor.getDireccion()
        );
    }

    //Getters
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getEmail(){return email;}
    public String getTelefono(){return telefono;}
    public String getDireccion(){return direccion;}
}
