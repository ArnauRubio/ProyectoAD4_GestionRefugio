package Idao;

import Dao.Dao;
import Models.Animal;
import Models.RegistroMedico;

public interface IAnimalDao extends IDao<Animal, Integer> {
    /**
     * Calcula el siguiente _id libre (el mayor + 1).
     */
    int siguienteId();

    /**
     * Añade un registro al array historialMedico ($push).
     */
    boolean anadirRegistroMedico(int idAnimal, RegistroMedico registro);

    /**
     * Cambia solo el campo estado ($set).
     */
    boolean cambiarEstado(int idAnimal, String estado);
}
