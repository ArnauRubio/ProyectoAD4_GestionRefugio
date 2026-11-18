package Idao;

import Models.Adoptante;

public interface IAdoptanteDao extends IDao<Adoptante, Integer> {
    int siguienteId();

    Adoptante buscarPorId(Integer id);

    Adoptante buscarPorDni(String dni);

    boolean anadirPreferencia(int idAdoptante, String preferencia);
}
