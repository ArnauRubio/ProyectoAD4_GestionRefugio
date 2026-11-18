import Dao.AdopcionDao;
import Dao.AdoptanteDao;
import Dao.AnimalDao;
import Models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static List<Animal> animales = new ArrayList<>();
    static List<Adoptante> adoptantes = new ArrayList<>();
    static List<Adopcion> adopciones = new ArrayList<>();

    static AnimalDao animalDao = new AnimalDao();
    static AdoptanteDao adoptanteDao = new AdoptanteDao();
    static AdopcionDao adopcionDao = new AdopcionDao();

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion = -1;
        do {
            System.out.print("=========\n" +
                    "\tMENÚ\n" +
                    "=========\n" +
                    "\t1) Animales\n" +
                    "\t2) Adoptantes\n" +
                    "\t3) Adopciones\n" +
                    "\t4) Sobre el refugio\n" +
                    "\t5) Salir\n" +
                    "--> Opción: "
            );
            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor no válido");
            } catch (IOException e) {
                System.out.println("Error");
            }

            switch (opcion) {
                case 1:
                    menuProveedores();
                    break;
                case 2:
                    menuPiezas();
                    break;
                case 3:
                    menuProyectos();
                    break;
                case 4:
                    menuGestiones();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Selecciona una opción válida");
            }
        } while (opcion != 5);
    }

    public static void cargarDatosIniciales() {

    }


    /**
     * Muestra las opciones para administrar piezas.
     */
    public static void menuAnimales() {
        int opcion = -1;
        do {
            System.out.println("------");
            System.out.print("=== Menú animales ===\n" +
                    "\t1) Alta de animal\n" +
                    "\t2) Baja de animal\n" +
                    "\t3) Modificar animal\n" +
                    "\t4) Listado de animales\n" +
                    "\t5) Búsqueda por nombre\n" +
                    "\t6) Búsqueda por especie\n" +
                    "\t7) Búsqueda por raza\n" +
                    "\t8) Volver\n" +
                    "--> Opción: "
            );
            try {
                opcion = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor no válido");
                continue;
            } catch (IOException e) {
                System.out.println("Error");
            }

            switch (opcion) {
                case 1:
                    crearPieza();
                    break;
                case 2:
                    editarPieza();
                    break;
                case 3:
                    borrarPieza();
                    break;
                case 4:
                    mostrarLista(piezas);
                    break;
                case 5:
                    buscarPiezaPorNombre();
                    break;
                case 6:
                    buscarPiezaPorCodigo();
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("*Volviendo al menú principal");
                    break;
                default:
                    System.out.println("Error: Introduce un numero entero del 1 al 7");
            }
        } while (opcion != 8);
    }

    /**
     * Pide los datos de un animal nuevo y lo guarda
     */
    public static void altaAnimal() throws IOException {
        System.out.println("------");
        System.out.println("--- Alta animal ---");

        System.out.print("Nombre: ");
        String nombre = br.readLine();
        System.out.print("Especie: ");
        String especie = br.readLine();
        System.out.print("Raza: ");
        String raza = br.readLine();

        int edad;
        try {
            System.out.print("Edad: ");
            edad = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("Error. Introduce un entero");
            return;
        }

        System.out.print("Sexo (M | H): ");
        Sexo sexo;
        if (br.readLine().equalsIgnoreCase("M")) {
            sexo = Sexo.MACHO;
        } else if (br.readLine().equalsIgnoreCase("H")) {
            sexo = Sexo.HEMBRA;
        } else {
            System.out.println("Elige una de las opciones disponibles");
            return;
        }

        System.out.print("Descripción: ");
        String descripcion = br.readLine();

        System.out.print("Fecha ingreso (yyyy-mm-dd): ");
        LocalDate fechaIngreso = LocalDate.parse(br.readLine());


        System.out.print("Estado (Disponible | Reservado | Adoptado): ");
        EstadoAdopcion estado;
        if (br.readLine().equalsIgnoreCase("disponible")) {
            estado = EstadoAdopcion.DISPONIBLE;
        } else if (br.readLine().equalsIgnoreCase("reservado")) {
            estado = EstadoAdopcion.PENDIENTE;
        } else if (br.readLine().equalsIgnoreCase("adoptado")) {
            estado = EstadoAdopcion.ADOPTADO;
        } else {
            System.out.println("Elige una de las opciones disponibles.");
            return;
        }

        Animal animal = new Animal(1, nombre, especie, raza, edad, sexo, descripcion, fechaIngreso, estado);

        //Dao
        Pieza pieza = new Pieza(codigo, nombre, precio, descripcion);
        piezas.add(pieza);
        piezaDao.create(pieza);
    }
}
