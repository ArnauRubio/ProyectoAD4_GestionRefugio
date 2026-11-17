package Dao;

import Idao.IDao;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class Dao<T, K> implements IDao<T, K> {
    protected final MongoCollection<Document> coleccion;

    protected Dao(String nombreColeccion) {
        coleccion = GestionDao.getInstancia().getBaseDatos().getCollection(nombreColeccion);
    }

    // ---------- Lo que tiene que hacer cada hija ----------

    /**
     * Objeto Java -> documento de MongoDB.
     */
    protected Document aDocumento(T objeto) {
        return null;
    }

    /**
     * Documento de MongoDB -> objeto Java.
     */
    protected T aObjeto(Document doc) {
        return null;
    }

    /**
     * Devuelve el _id del objeto.
     */
    protected K getId(T objeto) {
        return null;
    }

    /**
     * Orden de los listados. Por defecto por _id; una hija puede cambiarlo (@Override).
     */
    protected Bson orden() {
        return Sorts.ascending("_id");
    }

    // ---------- CRUD común ----------

    @Override
    public void insertar(T objeto) {
        coleccion.insertOne(aDocumento(objeto));
    }

    @Override
    public T buscarPorId(K id) {
        Document d = coleccion.find(Filters.eq("_id", id)).first();
        return d == null ? null : aObjeto(d);
    }

    @Override
    public List<T> listarTodos() {
        // ne = "not equal": así también salen los documentos que no tienen el campo
        return buscar(Filters.ne("eliminado", true));
    }

    @Override
    public boolean actualizar(T objeto) {
        // replaceOne sustituye el documento entero por el nuevo
        return coleccion.replaceOne(Filters.eq("_id", getId(objeto)), aDocumento(objeto))
                .getMatchedCount() > 0;
    }

    @Override
    public boolean eliminar(K id) {
        return coleccion.deleteOne(Filters.eq("_id", id)).getDeletedCount() > 0;
    }

    @Override
    public boolean marcarEliminado(K id) {
        return coleccion.updateOne(Filters.eq("_id", id), Updates.set("eliminado", true))
                .getMatchedCount() > 0;
    }

    @Override
    public List<Document> listarDocumentos() {
        return coleccion.find(Filters.ne("eliminado", true)).sort(orden()).into(new ArrayList<>());
    }

    // ---------- Ayudas para las hijas ----------

    /**
     * Ejecuta un filtro y convierte el resultado en lista de objetos.
     */
    protected List<T> buscar(Bson filtro) {
        List<T> lista = new ArrayList<>();
        for (Document d : coleccion.find(filtro).sort(orden())) {
            lista.add(aObjeto(d));
        }
        return lista;
    }

    /**
     * Siguiente _id numérico libre (el mayor + 1). Lo usan animales y adopciones.
     */
    public int siguienteId() {
        Document ultimo = coleccion.find().sort(Sorts.descending("_id")).first();
        return ultimo == null ? 1 : leerEntero(ultimo, "_id") + 1;
    }

    /**
     * Lee un número entero de un documento.
     * Si los datos se insertaron desde mongosh o Compass, a veces se guardan como
     * double (5.0) en lugar de int (5); así funciona en los dos casos.
     */
    protected static int leerEntero(Document doc, String campo) {
        Object valor = doc.get(campo);
        return valor instanceof Number ? ((Number) valor).intValue() : 0;
    }
}
