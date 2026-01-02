package tech.hospital.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;

public class ConnectionDB {

    //Le pasamos la url a nuestra base de datos la cual se encunetra en la nube
    private String url = "mongodb+srv://admin:admin1234@cluster0.gr0v5oi.mongodb.net/Hospital";
    private MongoClient mongoClient;
    private MongoDatabase database;

    public ConnectionDB(){
        try{
            //Establecemos la conexion a nuestra base de datos
            mongoClient = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyConnectionString(new ConnectionString(url))
                            .build()
            );

            database = mongoClient.getDatabase("Hospital");

            System.out.println("Conexion exitosa a la base de datos ");

        }catch(MongoException e){
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }

    //Getters
    public MongoClient getMongoClient(){return mongoClient;}
    public MongoDatabase getDatabase(){return database;}

    //Obtenemos la coleccion de nuestra base de datos
    public MongoCollection<Document> getCollection(String nameCollection){
        return database.getCollection(nameCollection);
    }

    //Cerramos la conexion de nuestra base de datos
    public void connectionClose(){
        if(mongoClient != null){
            mongoClient.close();
            System.out.println("Conexion cerrada exitosamente");
        }
    }
}
