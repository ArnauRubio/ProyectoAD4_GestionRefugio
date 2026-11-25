package Dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConexionBD {
    private static final String URI = "mongodb://localhost:27017/?serverSelectionTimeoutMS=3000";
    private static final String NOMBRE_BD = "refugio";

    private static ConexionBD instancia;

    private final MongoClient cliente;
    private final MongoDatabase baseDatos;
    private boolean cerrada;

    private ConexionBD() {
        cliente = MongoClients.create(URI);
        baseDatos = cliente.getDatabase(NOMBRE_BD);
    }

    public static synchronized ConexionBD getInstancia() {
        if (instancia == null || instancia.cerrada) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public MongoDatabase getBaseDatos() {
        return baseDatos;
    }

    public MongoCollection<Document> getColeccion(String nombre) {
        return baseDatos.getCollection(nombre);
    }

    public boolean comprobarConexion() {
        try {
            baseDatos.runCommand(new Document("ping", 1));
            return true;
        } catch (MongoException e) {
            return false;
        }
    }

    public synchronized void cerrar() {
        if (!cerrada) {
            cliente.close();
            cerrada = true;
            if (instancia == this) {
                instancia = null;
            }
        }
    }
}
