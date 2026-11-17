package org.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App {
    public static void main(String[] args) {

        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("RefugioDB");

        MongoCollection<Document> animales = db.getCollection("Animales");

        Document animal = new Document("nombre", "pepe")
                .append("especie", "perro")
                .append("raza", "rottweiler")
                .append("edad", 10)
                .append("sexo", "macho")
                .append("descripcion", "muy malo, y vilento, calvo y feroz")
                .append("fechaIngreso", 2019-06-07);

        animales.insertOne(animal);

        mongo.close();
    }
}