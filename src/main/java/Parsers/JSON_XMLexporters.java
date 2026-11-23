package Parsers;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.io.*;
import java.util.List;
import java.util.Map;

public class JSON_XMLexporters {
    private static final String CARPETA = "exportaciones";

    public static void exportar(List<Document> documentos, String nombreFichero, String raiz, String elemento) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ruta = CARPETA + File.separator + nombreFichero;

        int opcionFormato;
        if (documentos.isEmpty()) {
            System.out.println("No hay datos que exportar.");
            return;
        }
        System.out.println("Elige como quieres exportar los datos: \n" +
                "1- JSON \n" +
                "2- XML \n" +
                "3- Ambos \n" +
                "4- Cancelar");
        opcionFormato = Integer.parseInt(br.readLine());

        switch (opcionFormato) {
            case 1:
                exportarJson(documentos, ruta + ".json");
                System.out.println("✓ Exportado a " + ruta + ".json");
                break;
            case 2:
                exportarXml(documentos, raiz, elemento, ruta + ".xml");
                System.out.println("✓ Exportado a " + ruta + ".xml");
                break;
            case 3:
                exportarJson(documentos, ruta + ".json");
                System.out.println("✓ Exportado a " + ruta + ".json");

                exportarXml(documentos, raiz, elemento, ruta + ".xml");
                System.out.println("✓ Exportado a " + ruta + ".xml");
                break;
            case 4:
                break;
            default:
                System.out.println("Introduce un nuemro entero del 1 al 4");
        }
    }

    public static void exportarJson(List<Document> documentos, String fichero) throws IOException {
        // indent(true) -> JSON bonito con saltos de línea
        JsonWriterSettings ajustes = JsonWriterSettings.builder().outputMode(JsonMode.RELAXED).indent(true).build();

        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {
            pw.println("[");
            for (int i = 0; i < documentos.size(); i++) {
                pw.print(documentos.get(i).toJson(ajustes));
                if (i < documentos.size() - 1) {
                    pw.println(",");
                }
            }
            pw.println();
            pw.println("]");
        }
    }

    public static void exportarXml(List<Document> documentos, String raiz, String elemento, String fichero)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<").append(raiz).append(">\n");
        for (Document d : documentos) {
            escribirValor(sb, elemento, d, 1);
        }
        sb.append("</").append(raiz).append(">\n");

        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {
            pw.print(sb);
        }
    }

    /**
     * Escribe una etiqueta. Es RECURSIVO: si el valor es un subdocumento o una lista,
     * se vuelve a llamar para cada campo/elemento de dentro.
     */
    private static void escribirValor(StringBuilder sb, String etiqueta, Object valor, int nivel) {
        StringBuilder sangriaBuilder = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sangriaBuilder.append("  ");
        }
        String sangria = sangriaBuilder.toString();

        if (valor instanceof Document) {
            sb.append(sangria).append("<").append(etiqueta).append(">\n");
            for (Map.Entry<String, Object> campo : ((Document) valor).entrySet()) {
                escribirValor(sb, campo.getKey(), campo.getValue(), nivel + 1);
            }
            sb.append(sangria).append("</").append(etiqueta).append(">\n");

        } else if (valor instanceof List) {
            sb.append(sangria).append("<").append(etiqueta).append(">\n");
            for (Object item : (List<?>) valor) {
                escribirValor(sb, "item", item, nivel + 1);
            }
            sb.append(sangria).append("</").append(etiqueta).append(">\n");

        } else {
            String texto = valor == null ? "" : escapar(valor.toString());
            sb.append(sangria).append("<").append(etiqueta).append(">")
                    .append(texto)
                    .append("</").append(etiqueta).append(">\n");
        }
    }

    /**
     * Sustituye los caracteres que no se pueden poner tal cual en XML.
     */
    private static String escapar(String texto) {
        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
