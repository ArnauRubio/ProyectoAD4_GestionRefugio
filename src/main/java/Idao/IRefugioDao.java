package Idao;

import Models.Refugio;

public interface IRefugioDao {
    /**
     * Devuelve los datos del refugio o null si todavía no hay.
     */
    Refugio obtener();

    /**
     * Inserta o reemplaza los datos del refugio.
     */
    void guardar(Refugio refugio);
}
