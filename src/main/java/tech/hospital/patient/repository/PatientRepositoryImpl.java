package tech.hospital.patient.repository;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoException;
import com.mongodb.MongoQueryException;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.ascending;

import tech.hospital.connection.ConnectionDB;
import tech.hospital.patient.model.Patient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {

    private Patient patient;
    private ConnectionDB connectionDB;
    private MongoCollection<Document> collection;

    public  PatientRepositoryImpl(){
        this.connectionDB = new ConnectionDB();
        collection = connectionDB.getCollection("Patient"); //Obtenemos la coleccion
    }

    //Buscamos un paciente por su id
    public Optional<Patient> findById(int id){
        try{
            //Obtenemos los datos del documento
            Document document = collection.find(eq("id", id)).first();

            //Leemos la fecha desde mongodb
            Date fecha = document.getDate("fechaRegistro");

            LocalDateTime fechaRegistro = null;

            //Obtenemos los datos guardados en nuestro documento
            if(document != null){
                Patient patient = new Patient(
                        document.getInteger("id"),
                        document.getString("nombre"),
                        document.getString("apellido"),
                        document.getString("email"),
                        document.getInteger("edad"),
                        document.getString("genero"),
                        document.getString("telefono"),
                        document.getString("direccion"),
                        fechaRegistro
                );

                return Optional.of(patient);

            }else{
                return Optional.empty();
            }

        }catch(MongoQueryException e){
            System.out.println("Error al buscar los pacientes: " + e.getMessage());
            return Optional.empty();
        }
    }

    //Metodo para mostrar todos los pacientes
    public List<Patient> findAll() {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            for (Document document : collection.find().sort(ascending("id"))) {

                Date fecha = document.getDate("fechaRegistro");

                LocalDateTime fechaRegistro = null;

                if (fecha != null) {
                    fechaRegistro = fecha.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                }

                Patient patient = new Patient(
                        document.getInteger("id"),
                        document.getString("nombre"),
                        document.getString("apellido"),
                        document.getString("email"),
                        document.getInteger("edad"),
                        document.getString("genero"),
                        document.getString("telefono"),
                        document.getString("direccion"),
                        fechaRegistro
                );

                patients.add(patient);
            }

        } catch (MongoQueryException e) {
            System.out.println("Error al mostrar los pacientes: " + e.getMessage());
        }

        return patients;
    }

    //Metodo para obtener un paciente por su nombre
    public Optional<Patient> findByName(String name){
        try {
            //Obtenemos los datos del documento
            Document document = collection.find(eq("nombre", name)).first();

            //Leemos la fecha desde mongodb
            Date fecha = document.getDate("fechaRegistro");

            LocalDateTime fechaRegistro = null;

            if(document != null){
                Patient patient = new Patient(
                        document.getInteger("id"),
                        document.getString("nombre"),
                        document.getString("apellido"),
                        document.getString("email"),
                        document.getInteger("edad"),
                        document.getString("genero"),
                        document.getString("telefono"),
                        document.getString("direccion"),
                        fechaRegistro
                );
                return Optional.of(patient);
            }
            else{
                return Optional.empty();
            }

        }catch(MongoQueryException e){
            System.out.println("Error al mostrar los pacientes: " + e.getMessage());
            return Optional.empty();
        }
    }

    //Metodo para guardar un paciente
    public Patient save(Patient patient){
        try{
            //Creamos un documento y lo agregamos a la coleccion
            Document document = new Document("id", patient.getId())
                    .append("nombre", patient.getNombre())
                    .append("apellido", patient.getApellido())
                    .append("email", patient.getEmail())
                    .append("edad", patient.getEdad())
                    .append("genero", patient.getGenero())
                    .append("telefono", patient.getTelefono())
                    .append("direccion", patient.getDireccion())
                    .append("fechaRegistro", patient.getFechaRegistro());

            collection.insertOne(document);
            return patient;

        }catch(MongoBulkWriteException e){
            System.out.println("Error al guardar el paciente: " + e.getMessage());
            return null;
        }
    }

    //Metodo para actualizar los datos de un paciente
    public Patient update(Patient patient){
        try{
            //Creamos un nuevo documento para guardar los nuevos datos
            Document document = new Document("$set", new Document("nombre", patient.getNombre())
                    .append("apellido", patient.getApellido())
                    .append("email", patient.getEmail())
                    .append("edad", patient.getEdad())
                    .append("genero", patient.getGenero())
                    .append("telefono", patient.getTelefono())
                    .append("direccion", patient.getDireccion()));

            collection.updateOne(eq("id", patient.getId()), document);
            return  patient;

        }catch(MongoBulkWriteException e){
            System.out.println("Error al actualizar los datos de el paciente: " + e.getMessage());
            return null;
        }
    }

    //Metodo para eliminar un paciente
    public Boolean deleteById(int id){
        try{
            DeleteResult deleteResult = collection.deleteOne(eq("id", id));
            return deleteResult.getDeletedCount() > 0; //Devolvemos true solo si se elimino el paciente
        }catch(MongoException e){
            System.out.println("Error al eliminar el paciente: " + e.getMessage());
            return false;
        }
    }
}
