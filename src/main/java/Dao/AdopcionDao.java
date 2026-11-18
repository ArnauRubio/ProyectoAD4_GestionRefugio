package Dao;

import Idao.IAdopcionDao;
import Models.Adopcion;
import Models.Seguimiento;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdopcionDao extends Dao<Adopcion, Integer> implements IAdopcionDao {
    public AdopcionDao() {
        super("adopciones");
    }

    // ---------- Conversión Adopcion <-> Document ----------

    @Override
    protected Document aDocumento(Adopcion a) {
        List<Document> seguimientos = new ArrayList<>();
        for (Seguimiento s : a.getSeguimiento()) {
            seguimientos.add(new Document("fecha", s.getFecha()).append("comentario", s.getComentario()));
        }
        return new Document("_id", a.getId())
                .append("idAnimal", a.getIdAnimal())            // REFERENCIA a animales
                .append("idAdoptante", a.getIdAdoptante())    // REFERENCIA a adoptantes
                .append("fechaSolicitud", a.getFechaSolicitud())
                .append("fechaResolucion", a.getFechaResolucion())
                .append("estado", a.getEstado())
                .append("observaciones", a.getObservaciones())
                .append("seguimiento", seguimientos)            // EMBEBIDO
                .append("eliminado", a.isEliminado());
    }

    @Override
    protected Adopcion aObjeto(Document d) {
        Adopcion a = new Adopcion();
        a.setId(leerEntero(d, "_id"));
        a.setIdAnimal(leerEntero(d, "idAnimal"));
        a.setIdAdoptante(leerEntero(d, "idAdoptante"));
        a.setFechaSolicitud(d.getDate("fechaSolicitud"));
        a.setFechaResolucion(d.getDate("fechaResolucion"));
        a.setEstado(d.getString("estado"));
        a.setObservaciones(d.getString("observaciones"));
        a.setEliminado(d.getBoolean("eliminado", false));

        for (Document s : d.getList("seguimiento", Document.class, new ArrayList<>())) {
            a.getSeguimiento().add(new Seguimiento(s.getDate("fecha"), s.getString("comentario")));
        }
        return a;
    }

    @Override
    protected Integer getId(Adopcion a) {
        return a.getId();
    }

    // ---------- Específicos ----------

    @Override
    public List<Adopcion> buscarPorAdoptante(int idAdoptante) {
        return buscar(Filters.and(Filters.eq("idAdoptante", idAdoptante), Filters.ne("eliminado", true)));
    }

    @Override
    public List<Adopcion> buscarPorAnimal(int idAnimal) {
        return buscar(Filters.and(Filters.eq("idAnimal", idAnimal), Filters.ne("eliminado", true)));
    }

    @Override
    public boolean cambiarEstado(int idAdopcion, String estado, Date fechaResolucion) {
        return coleccion.updateOne(Filters.eq("_id", idAdopcion),
                        Updates.combine(Updates.set("estado", estado), Updates.set("fechaResolucion", fechaResolucion)))
                .getMatchedCount() > 0;
    }

    @Override
    public boolean anadirSeguimiento(int idAdopcion, Seguimiento s) {
        Document d = new Document("fecha", s.getFecha()).append("comentario", s.getComentario());
        return coleccion.updateOne(Filters.eq("_id", idAdopcion), Updates.push("seguimiento", d))
                .getMatchedCount() > 0;
    }
}
