package Dao;

import Idao.IRefugioDao;
import Models.Direccion;
import Models.Refugio;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;

public class RefugioDao implements IRefugioDao {
    private static final int ID_REFUGIO = 1;   // solo hay un documento
    private final MongoCollection<Document> coleccion;

    public RefugioDao() {
        coleccion = GestionDao.getInstancia().getBaseDatos().getCollection("refugio");
    }

    @Override
    public Refugio obtener() {
        Document d = coleccion.find(Filters.eq("_id", ID_REFUGIO)).first();
        if (d == null) {
            return null;
        }
        Refugio r = new Refugio();
        r.setNombre(d.getString("nombre"));
        r.setCif(d.getString("cif"));
        r.setFechaApertura(d.getString("fechaApertura"));
        r.setTelefono(d.getString("telefono"));
        r.setEmail(d.getString("email"));
        Document dir = d.get("direccion", Document.class);
        if (dir != null) {
            r.setDireccion(new Direccion(dir.getString("calle"), dir.getString("ciudad"),
                    dir.getString("codigoPostal")));
        }
        return r;
    }

    @Override
    public void guardar(Refugio r) {
        Document dir = new Document("calle", r.getDireccion().getCalle())
                .append("ciudad", r.getDireccion().getCiudad())
                .append("codigoPostal", r.getDireccion().getCodigoPostal());
        Document d = new Document("_id", ID_REFUGIO)
                .append("nombre", r.getNombre())
                .append("cif", r.getCif())
                .append("fechaApertura", r.getFechaApertura())
                .append("direccion", dir)
                .append("telefono", r.getTelefono())
                .append("email", r.getEmail());
        // upsert(true): si no existe lo inserta, si existe lo reemplaza
        coleccion.replaceOne(Filters.eq("_id", ID_REFUGIO), d, new ReplaceOptions().upsert(true));
    }
}
