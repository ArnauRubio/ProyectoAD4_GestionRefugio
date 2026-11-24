package Dao;

import Idao.IAdoptanteDao;
import Models.Adoptante;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

public class AdoptanteDao extends Dao<Adoptante, Integer> implements IAdoptanteDao {
    public AdoptanteDao() {
        super("adoptantes");
    }

    // ---------- Conversión Adoptante <-> Document ----------

    @Override
    protected Document aDocumento(Adoptante a) {

        return new Document("_id", a.getId())
                .append("dni", a.getDni())
                .append("nombre", a.getNombre())
                .append("apellidos", a.getApellidos())
                .append("telefono", a.getTelefono())
                .append("direccion", a.getDireccion())
                .append("email", a.getEmail())
                .append("fechaRegistro", a.getFechaRegistro())
                .append("preferencias", a.getPreferencias())
                .append("eliminado", !a.isEnRefugio());
    }

    @Override
    protected Adoptante aObjeto(Document d) {
        Adoptante a = new Adoptante();
        a.setId(leerEntero(d, "_id"));
        a.setDni(d.getString("dni"));
        a.setNombre(d.getString("nombre"));
        a.setApellidos(d.getString("apellidos"));
        a.setTelefono(d.getString("telefono"));
        a.setDireccion(d.getString("direccion"));
        a.setEmail(d.getString("email"));
        a.setFechaRegistro(d.getDate("fechaRegistro"));
        a.setPreferencias(d.getString("preferencias"));
        a.setEnRefugio(!d.getBoolean("eliminado", false));
        return a;
    }

    @Override
    protected Integer getId(Adoptante a) {
        return a.getId();
    }

    /**
     * Los adoptantes se listan por apellidos en vez de por _id.
     */
    @Override
    protected Bson orden() {
        return Sorts.ascending("apellidos", "nombre");
    }

    // ---------- Específicos ----------

    public Adoptante buscarPorDni(String dni) {
        Document d = coleccion.find(Filters.eq("dni", dni)).first();
        return d == null ? null : aObjeto(d);
    }

    @Override
    public boolean anadirPreferencia(int idAdoptante, String preferencia) {
        return coleccion.updateOne(Filters.eq("_id", idAdoptante), Updates.addToSet("preferencias", preferencia))
                .getMatchedCount() > 0;
    }

    @Override
    public Adoptante buscarPorId(Integer id) {
        return super.buscarPorId(id);
    }

    @Override
    public boolean eliminar(Integer id) {
        return false;
    }

    @Override
    public boolean marcarEliminado(Integer id) {
        return false;
    }
}
