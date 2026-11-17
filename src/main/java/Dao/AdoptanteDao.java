package Dao;

import Idao.IAdoptanteDao;
import Models.Adoptante;
import Models.Direccion;
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
        Direccion dir = a.getDireccion();
        Document docDireccion = new Document("calle", dir.getCalle())   // subdocumento EMBEBIDO
                .append("ciudad", dir.getCiudad())
                .append("codigoPostal", dir.getCodigoPostal());

        return new Document("_id", a.getDni())
                .append("nombre", a.getNombre())
                .append("apellidos", a.getApellidos())
                .append("telefono", a.getTelefono())
                .append("direccion", docDireccion)
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

        Document dir = d.get("direccion", Document.class);
        if (dir != null) {
            a.setDireccion(new Direccion(dir.getString("calle"), dir.getString("ciudad"),
                    dir.getString("codigoPostal")));
        }
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
