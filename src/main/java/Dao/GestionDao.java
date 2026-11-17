package Dao;

import Idao.IGestionDao;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class GestionDao implements IGestionDao {

    // serverSelectionTimeoutMS=3000 -> si el servidor no está arrancado, falla en 3 s y no en 30 s
    private static final String URI = "mongodb://localhost:27017/?serverSelectionTimeoutMS=3000";
    private static final String NOMBRE_BD = "refugio";

    private static GestionDao instancia;   // el único objeto

    private final MongoClient cliente;
    private final MongoDatabase baseDatos;

    // Constructor privado: nadie puede hacer "new GestionDao()" desde fuera
    private GestionDao() {
        cliente = MongoClients.create(URI);
        baseDatos = cliente.getDatabase(NOMBRE_BD);
    }

    /**
     * Devuelve el único GestionDao. La primera vez lo crea (abre la conexión).
     */
    public static GestionDao getInstancia() {
        if (instancia == null) {
            instancia = new GestionDao();
        }
        return instancia;
    }

    // ======================= CONEXIÓN =======================

    @Override
    public MongoDatabase getBaseDatos() {
        return baseDatos;
    }

    /**
     * Hace un "ping" al servidor para saber si está arrancado.
     */
    @Override
    public boolean comprobarConexion() {
        try {
            baseDatos.runCommand(new Document("ping", 1));
            return true;
        } catch (MongoException e) {
            return false;
        }
    }

    /**
     * Crea los índices (si ya existen, MongoDB no hace nada).
     */
    @Override
    public void crearIndices() {
        baseDatos.getCollection("animales").createIndex(Indexes.ascending("especie"));
        baseDatos.getCollection("animales").createIndex(Indexes.ascending("estado"));
        baseDatos.getCollection("adoptantes").createIndex(Indexes.ascending("apellidos", "nombre"));
        baseDatos.getCollection("adoptantes").createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
        baseDatos.getCollection("adopciones").createIndex(Indexes.ascending("idAnimal"));
        baseDatos.getCollection("adopciones").createIndex(Indexes.ascending("dniAdoptante"));
        baseDatos.getCollection("adopciones").createIndex(Indexes.descending("fechaSolicitud"));
    }

    /**
     * Cierra la conexión al salir del programa.
     */
    @Override
    public void cerrar() {
        cliente.close();
        instancia = null;
    }

    // ======================= METADATOS =======================

    @Override
    public Document infoBaseDatos() {
        // dbStats es un comando de MongoDB que devuelve estadísticas de la BD
        Document stats = baseDatos.runCommand(new Document("dbStats", 1));
        List<String> colecciones = baseDatos.listCollectionNames().into(new ArrayList<>());

        return new Document("nombre", baseDatos.getName())
                .append("colecciones", colecciones)
                .append("numColecciones", colecciones.size())
                .append("numDocumentos", stats.get("objects"))
                .append("numIndices", stats.get("indexes"))
                .append("tamanoDatosBytes", stats.get("dataSize"));
    }

    @Override
    public List<Document> infoColecciones() {
        List<Document> lista = new ArrayList<>();
        for (String nombre : baseDatos.listCollectionNames()) {
            MongoCollection<Document> col = baseDatos.getCollection(nombre);

            List<String> indices = new ArrayList<>();
            for (Document indice : col.listIndexes()) {
                indices.add(indice.getString("name"));
            }
            lista.add(new Document("nombre", nombre)
                    .append("numDocumentos", col.countDocuments())
                    .append("indices", indices));
        }
        return lista;
    }

    @Override
    public List<Document> infoIndices() {
        List<Document> lista = new ArrayList<>();
        for (String nombre : baseDatos.listCollectionNames()) {
            for (Document indice : baseDatos.getCollection(nombre).listIndexes()) {
                // "key" es algo como { "especie": 1 } o { "apellidos": 1, "nombre": 1 }
                Document claves = indice.get("key", Document.class);
                List<String> campos = new ArrayList<>(claves.keySet());

                lista.add(new Document("coleccion", nombre)
                        .append("nombre", indice.getString("name"))
                        .append("campos", campos)
                        .append("tipo", tipoIndice(claves))
                        .append("unico", indice.getBoolean("unique", false)));
            }
        }
        return lista;
    }

    /**
     * Traduce los valores del índice (1, -1, "text"...) a un texto legible.
     */
    private String tipoIndice(Document claves) {
        String tipo = claves.size() > 1 ? "compuesto " : "simple ";
        Object valor = claves.values().iterator().next();
        if (valor instanceof Number) {
            tipo += ((Number) valor).intValue() == 1 ? "ascendente" : "descendente";
        } else {
            tipo += String.valueOf(valor);   // text, 2dsphere, hashed...
        }
        return tipo;
    }
}
