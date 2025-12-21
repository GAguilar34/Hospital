package tech.hospital.patient.model;

import java.time.LocalDateTime;

public class Patient {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private String genero;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaRegistro;

    public Patient(int id, String nombre, String apellido, String email, int edad, String genero, String telefono, String direccion, LocalDateTime fechaRegistro) {
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

    //Getters
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getEmail(){return email;}
    public int getEdad(){return edad;}
    public String getGenero(){return genero;}
    public String getTelefono(){return telefono;}
    public String getDireccion(){return direccion;}
    public LocalDateTime getFechaRegistro() {return fechaRegistro;}

    //Setters
    public void setId(int id){this.id = id;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setApellido(String apellido){this.apellido = apellido;}
    public void setEmail(String email){this.email = email;}
    public void setEdad(int edad){this.edad = edad;}
    public void setGenero(String genero){this.genero = genero;}
    public void setTelefono(String telefono){this.telefono = telefono;}
    public void setDireccion(String direccion){this.direccion = direccion;}
    public void setFechaRegistro(LocalDateTime fechaRegistro){this.fechaRegistro = fechaRegistro;}
}