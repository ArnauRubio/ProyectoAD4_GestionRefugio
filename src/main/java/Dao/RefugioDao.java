package Dao;

import Idao.IRefugioDao;
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
        r.setFechaApertura(d.getDate("fechaApertura"));
        r.setTelefono(d.getString("telefono"));
        r.setEmail(d.getString("email"));
        r.setDireccion( d.getString("direccion"));
        return r;
    }

    @Override
    public void guardar(Refugio r) {
        Document d = new Document("_id", ID_REFUGIO)
                .append("nombre", r.getNombre())
                .append("cif", r.getCif())
                .append("fechaApertura", r.getFechaApertura())
                .append("direccion", r.getDireccion())
                .append("telefono", r.getTelefono())
                .append("email", r.getEmail());
        // upsert(true): si no existe lo inserta, si existe lo reemplaza
        coleccion.replaceOne(Filters.eq("_id", ID_REFUGIO), d, new ReplaceOptions().upsert(true));
    }
}
