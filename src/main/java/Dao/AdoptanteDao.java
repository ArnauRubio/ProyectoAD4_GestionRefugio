package Dao;

import Idao.IAdoptanteDao;
import Models.Adoptante;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class AdoptanteDao extends Dao<Adoptante, String> implements IAdoptanteDao {
    public AdoptanteDao() {
        super("adoptantes");
    }

    // ---------- Conversión Adoptante <-> Document ----------

    @Override
    protected Document aDocumento(Adoptante a) {

        return new Document("_id", a.getDni())
                .append("nombre", a.getNombre())
                .append("apellidos", a.getApellidos())
                .append("telefono", a.getTelefono())
                .append("direccion", a.getDireccion())
                .append("email", a.getEmail())
                .append("fechaRegistro", a.getFechaRegistro())
                .append("preferencias", a.getPreferencias())          // array de String
                .append("eliminado", a.isEliminado());
    }

    @Override
    protected Adoptante aObjeto(Document d) {
        Adoptante a = new Adoptante();
        a.setDni(d.getString("_id"));
        a.setNombre(d.getString("nombre"));
        a.setApellidos(d.getString("apellidos"));
        a.setTelefono(d.getString("telefono"));
        a.setEmail(d.getString("email"));
        a.setFechaRegistro(d.getDate("fechaRegistro"));
        a.setEliminado(d.getBoolean("eliminado", false));
        a.setDireccion(d.getString("direccion"));
        a.setPreferencias(d.getList("preferencias", String.class, new ArrayList<>()));
        return a;
    }

    @Override
    protected String getId(Adoptante a) {
        return a.getDni();
    }

    /**
     * Los adoptantes se listan por apellidos en vez de por _id.
     */
    @Override
    protected Bson orden() {
        return Sorts.ascending("apellidos", "nombre");
    }

    // ---------- Específicos ----------

    @Override
    public boolean anadirPreferencia(String dni, String preferencia) {
        return coleccion.updateOne(Filters.eq("_id", dni), Updates.addToSet("preferencias", preferencia))
                .getMatchedCount() > 0;
    }
}
