package Idao;

import Dao.Dao;
import Models.Adopcion;
import Models.Seguimiento;

import java.util.List;

public interface IAdopcionDao extends IDao<Adopcion, Integer> {
    int siguienteId();

    /**
     * Todas las solicitudes/adopciones de un adoptante.
     */
    List<Adopcion> buscarPorAdoptante(String dni);

    /**
     * Todas las solicitudes/adopciones de un animal.
     */
    List<Adopcion> buscarPorAnimal(int idAnimal);

    /**
     * Cambia el estado y guarda la fecha de resolución.
     */
    boolean cambiarEstado(int idAdopcion, String estado, String fechaResolucion);

    /**
     * Añade un seguimiento al array seguimiento ($push).
     */
    boolean anadirSeguimiento(int idAdopcion, Seguimiento seguimiento);
}
