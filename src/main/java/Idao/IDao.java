package Idao;

import org.bson.Document;

import java.util.List;

public interface IDao<T, K> {
    void insertar(T objeto);

    T buscarPorId(K id);

    /**
     * Devuelve los documentos NO marcados como eliminados.
     */
    List<T> listarTodos();

    boolean actualizar(T objeto);

    /**
     * Borrado físico: el documento desaparece de la colección.
     */
    boolean eliminar(K id);

    /**
     * Borrado lógico: el documento se queda, pero con eliminado = true.
     */
    boolean marcarEliminado(K id);

    /**
     * Devuelve los documentos tal cual (Document) para exportarlos a JSON/XML.
     */
    List<Document> listarDocumentos();
}
