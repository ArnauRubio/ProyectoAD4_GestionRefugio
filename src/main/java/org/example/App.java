package org.example;

import Dao.ConexionBD;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        ConexionBD conexion = ConexionBD.getInstancia();
        try {
            if (!conexion.comprobarConexion()) {
                System.out.println("No se pudo conectar con MongoDB en localhost:27017.");
                return;
            }

            MongoCollection<Document> animales = conexion.getColeccion("animales");
            System.out.println("Conectado a la base de datos: " + conexion.getBaseDatos().getName());
            System.out.println("Animales guardados: " + animales.countDocuments());
        } finally {
            conexion.cerrar();
        }
    }
}