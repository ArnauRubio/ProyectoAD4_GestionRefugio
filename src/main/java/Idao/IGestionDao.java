package Idao;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public interface IGestionDao {
    // ----- Conexión -----
    MongoDatabase getBaseDatos();

    boolean comprobarConexion();

    void crearIndices();

    void cerrar();

    // ----- Metadatos (devuelven Document para mostrarlos y exportarlos fácilmente) -----
    Document infoBaseDatos();

    List<Document> infoColecciones();

    List<Document> infoIndices();
}
