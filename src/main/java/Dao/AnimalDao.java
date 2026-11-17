package Dao;

import Idao.IAnimalDao;
import Models.Animal;
import Models.RegistroMedico;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class AnimalDao extends Dao<Animal, Integer> implements IAnimalDao {
    public AnimalDao() {
        super("animales");
    }

    // ---------- Conversión Animal <-> Document ----------

    @Override
    protected Document aDocumento(Animal a) {
        // El historial médico se guarda como array de subdocumentos (EMBEBIDO)
        List<Document> historial = new ArrayList<>();
        for (RegistroMedico r : a.getHistorialMedico()) {
            historial.add(new Document("fecha", r.getFecha())
                    .append("descripcion", r.getDescripcion())
                    .append("veterinario", r.getVeterinario()));
        }
        return new Document("_id", a.getId())
                .append("nombre", a.getNombre())
                .append("especie", a.getEspecie())
                .append("raza", a.getRaza())
                .append("edad", a.getEdad())
                .append("sexo", a.getSexo())
                .append("descripcion", a.getDescripcion())
                .append("fechaIngreso", a.getFechaIngreso())
                .append("estado", a.getEstado())
                .append("historialMedico", historial)
                .append("eliminado", a.isEliminado());
    }

    @Override
    protected Animal aObjeto(Document d) {
        Animal a = new Animal();
        a.setId(leerEntero(d, "_id"));
        a.setNombre(d.getString("nombre"));
        a.setEspecie(d.getString("especie"));
        a.setRaza(d.getString("raza"));
        a.setEdad(leerEntero(d, "edad"));
        a.setSexo(d.getString("sexo"));
        a.setDescripcion(d.getString("descripcion"));
        a.setFechaIngreso(d.getString("fechaIngreso"));
        a.setEstado(d.getString("estado"));
        a.setEliminado(d.getBoolean("eliminado", false));

        for (Document h : d.getList("historialMedico", Document.class, new ArrayList<>())) {
            a.getHistorialMedico().add(new RegistroMedico(
                    h.getString("fecha"), h.getString("descripcion"), h.getString("veterinario")));
        }
        return a;
    }

    @Override
    protected Integer getId(Animal a) {
        return a.getId();
    }

    // ---------- Específicos de animales ----------

    @Override
    public boolean anadirRegistroMedico(int idAnimal, RegistroMedico reg) {
        Document d = new Document("fecha", reg.getFecha())
                .append("descripcion", reg.getDescripcion())
                .append("veterinario", reg.getVeterinario());
        return coleccion.updateOne(Filters.eq("_id", idAnimal), Updates.push("historialMedico", d))
                .getMatchedCount() > 0;
    }

    @Override
    public boolean cambiarEstado(int idAnimal, String estado) {
        return coleccion.updateOne(Filters.eq("_id", idAnimal), Updates.set("estado", estado))
                .getMatchedCount() > 0;
    }
}
