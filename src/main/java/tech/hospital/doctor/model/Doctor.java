package tech.hospital.doctor.model;

public class Doctor {

    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;

    public Doctor(int id,  String nombre, String apellido, String email, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    //Getters
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getEmail(){return email;}
    public String getTelefono(){return telefono;}
    public String getDireccion(){return direccion;}

    //Setters
    public void setId(int id){this.id = id;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setApellido(String apellido){this.apellido = apellido;}
    public void setEmail(String email){this.email = email;}
    public void setTelefono(String telefono){this.telefono = telefono;}
    public void setDireccion(String direccion){this.direccion = direccion;}

}
