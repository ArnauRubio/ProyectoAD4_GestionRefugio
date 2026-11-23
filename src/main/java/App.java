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

    // Listas con todos los objetos
    static List<Animal> all_animales = new ArrayList<>();
    static List<Adoptante> all_adoptantes = new ArrayList<>();
    static List<Adopcion> all_adopciones = new ArrayList<>();

    // Listas con los objetos dados de alta
    static List<Animal> animales = new ArrayList<>();
    static List<Adoptante> adoptantes = new ArrayList<>();
    static List<Adopcion> adopciones = new ArrayList<>();

    static AnimalDao animalDao = new AnimalDao();
    static AdoptanteDao adoptanteDao = new AdoptanteDao();
    static AdopcionDao adopcionDao = new AdopcionDao();

    public static void main(String[] args) throws IOException {
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
                    menuAnimales();
                    break;
                case 2:
                    menuAdoptantes();
                    break;
                case 3:
                    //menuProyectos();
                    break;
                case 4:
                    //menuGestiones();
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
    public static void menuAnimales() throws IOException {
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
                    altaAnimal();
                    break;
                case 2:
                    bajaAnimal();
                    break;
                case 3:
                    editarAnimal();
                    break;
                case 4:
                    mostrarLista(animales);
                    break;
                case 5:
                    buscarAnimalPorNombre();
                    break;
                case 6:
                    buscarAnimalPorEspecie();
                    break;
                case 7:
                    buscarAnimalPorRaza();
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
        Date fechaIngreso = new Date(br.readLine());

        System.out.print("Estado (Disponible | Reservado | Adoptado | Tratamiento): ");
        EstadoAnimal estado;
        if (br.readLine().equalsIgnoreCase("disponible")) {
            estado = EstadoAnimal.DISPONIBLE;
        } else if (br.readLine().equalsIgnoreCase("reservado")) {
            estado = EstadoAnimal.RESERVADO;
        } else if (br.readLine().equalsIgnoreCase("adoptado")) {
            estado = EstadoAnimal.ADOPTADO;
        } else if (br.readLine().equalsIgnoreCase("tratamiento")) {
            estado = EstadoAnimal.EN_TRATAMIENTO;
        } else {
            System.out.println("Elige una de las opciones disponibles.");
            return;
        }

        Animal animal = new Animal(nombre, especie, raza, edad, sexo, descripcion, fechaIngreso, estado);
        animales.add(animal);
        all_animales.add(animal);
        // BD
        animalDao.insertar(animal);
        System.out.println("✓ Animal dado de alta con id " + animal.getId());
    }

    /**
     * Elimina el animal seleccionado.
     */
    public static void bajaAnimal() {
        System.out.println("------");
        System.out.println("--- Baja de animal ---");

        // Se muestra lista de animales y eligen uno
        mostrarLista(animales);
        System.out.print("--> Opción: ");

        int opcion = -1;
        try {
            opcion = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor no válido");
            return;
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (opcion < 0 || opcion >= animales.size()) {
            System.out.println("Índice no válido");
            return;
        }

        Animal aEliminar = animales.get(opcion);
        aEliminar.setEliminado(true);
        animales.remove(aEliminar);

        // BD
        animalDao.eliminar(aEliminar.getId());
        System.out.println("✓ Animal borrado.");
    }

    /**
     * Permite cambiar los datos de un animal en alta.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void editarAnimal() throws IOException {
        System.out.println("------");
        System.out.println("--- Editar animal ---");

        // Se muestra lista de animales y eligen uno
        mostrarLista(animales);

        System.out.print("--> Editar: ");
        int opcion = -1;
        try {
            opcion = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor no válido");
            return;
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (opcion < 0 || opcion >= animales.size()) {
            System.out.println("Índice no válido");
            return;
        }

        // Nuevos valores
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

        System.out.print("Estado (Disponible | Reservado | Adoptado | Tratamiento): ");
        EstadoAnimal estado;
        if (br.readLine().equalsIgnoreCase("disponible")) {
            estado = EstadoAnimal.DISPONIBLE;
        } else if (br.readLine().equalsIgnoreCase("reservado")) {
            estado = EstadoAnimal.RESERVADO;
        } else if (br.readLine().equalsIgnoreCase("adoptado")) {
            estado = EstadoAnimal.ADOPTADO;
        } else if (br.readLine().equalsIgnoreCase("tratamiento")) {
            estado = EstadoAnimal.EN_TRATAMIENTO;
        } else {
            System.out.println("Elige una de las opciones disponibles.");
            return;
        }
        // Sobreescribir
        Animal animal = animales.get(opcion);

        animal.setNombre(nombre);
        animal.setEspecie(especie);
        animal.setRaza(raza);
        animal.setEdad(edad);
        animal.setSexo(sexo);
        animal.setDescripcion(descripcion);
        animal.setEstado(estado);

        // BD
        animalDao.actualizar(animal);
        System.out.println("✓ Animal modificado.");
    }

    /**
     * Busca y muestra animal por nombre.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void buscarAnimalPorNombre() throws IOException {
        System.out.println("------");
        System.out.println("--- Buscar animal por nombre ---");

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        boolean encontrado = false;
        for (Animal animal : animales) {
            if (animal.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("- Animal encontrado -");
                System.out.println("--> Nombre: " + animal.getNombre());
                System.out.println("--> Especie: " + animal.getEspecie());
                System.out.println("--> Raza: " + animal.getRaza());
                System.out.println("--> Edad: " + animal.getEdad());
                System.out.println("--> Sexo: " + animal.getSexo());
                System.out.println("--> Descripcion: " + animal.getDescripcion());
                System.out.println("--> Ingreso: " + animal.getFechaIngreso());
                System.out.println("--> En refugio: " + animal.isEliminado());

                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha podido encontrar el animal");
        }
    }

    /**
     * Busca y muestra animal por especie.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void buscarAnimalPorEspecie() throws IOException {
        System.out.println("------");
        System.out.println("--- Buscar animal por especie ---");

        System.out.print("Especie: ");
        String especie = br.readLine();

        boolean encontrado = false;
        for (Animal animal : animales) {
            if (animal.getEspecie().equalsIgnoreCase(especie)) {
                System.out.println("- Animal encontrado -");
                System.out.println("--> Nombre: " + animal.getNombre());
                System.out.println("--> Especie: " + animal.getEspecie());
                System.out.println("--> Raza: " + animal.getRaza());
                System.out.println("--> Edad: " + animal.getEdad());
                System.out.println("--> Sexo: " + animal.getSexo());
                System.out.println("--> Descripcion: " + animal.getDescripcion());
                System.out.println("--> Ingreso: " + animal.getFechaIngreso());
                System.out.println("--> En refugio: " + animal.isEliminado());

                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha podido encontrar el animal");
        }
    }

    /**
     * Busca y muestra animal por raza.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void buscarAnimalPorRaza() throws IOException {
        System.out.println("------");
        System.out.println("--- Buscar animal por raza ---");

        System.out.print("Raza: ");
        String raza = br.readLine();

        boolean encontrado = false;
        for (Animal animal : animales) {
            if (animal.getRaza().equalsIgnoreCase(raza)) {
                System.out.println("- Animal encontrado -");
                System.out.println("--> Nombre: " + animal.getNombre());
                System.out.println("--> Especie: " + animal.getEspecie());
                System.out.println("--> Raza: " + animal.getRaza());
                System.out.println("--> Edad: " + animal.getEdad());
                System.out.println("--> Sexo: " + animal.getSexo());
                System.out.println("--> Descripcion: " + animal.getDescripcion());
                System.out.println("--> Ingreso: " + animal.getFechaIngreso());
                System.out.println("--> En refugio: " + animal.isEliminado());

                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha podido encontrar el animal");
        }
    }

    /**
     * Muestra las opciones para administrar adoptantes.
     */
    public static void menuAdoptantes() throws IOException {
        int opcion = -1;
        do {
            System.out.println("------");
            System.out.print("=== Menú adoptantes ===\n" +
                    "\t1) Alta de adoptante\n" +
                    "\t2) Baja de adoptante\n" +
                    "\t3) Modificar adoptante\n" +
                    "\t4) Listado de adoptantes\n" +
                    "\t5) Búsqueda por nombre\n" +
                    "\t6) Búsqueda por apellidos\n" +
                    "\t7) Búsqueda por DNI\n" +
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
                    altaAdoptante();
                    break;
                case 2:
                    bajaAdoptante();
                    break;
                case 3:
                    editarAdoptante();
                    break;
                case 4:
                    mostrarLista(adoptantes);
                    break;
                case 5:
                    buscarAdoptantePorNombre();
                    break;
                case 6:
                    //buscarAdoptantePorApellidos();
                    break;
                case 7:
                    //buscarAnimalPorDni();
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
    public static void altaAdoptante() throws IOException {
        System.out.println("------");
        System.out.println("--- Alta adoptante ---");

        System.out.print("DNI: ");
        String dni = br.readLine();
        System.out.print("Nombre: ");
        String nombre = br.readLine();
        System.out.print("Apellidos: ");
        String apellidos = br.readLine();
        System.out.print("Tlf: ");
        String tlf = br.readLine();
        System.out.print("Dirección: ");
        String direccion = br.readLine();
        System.out.print("Email: ");
        String email = br.readLine();

        System.out.print("Fecha registro (yyyy-mm-dd): ");
        Date fechaRegistro = new Date(br.readLine());
        System.out.print("Preferencia: ");
        String pref = br.readLine();

        Adoptante adoptante = new Adoptante(dni, nombre, apellidos, tlf, direccion,
                email, fechaRegistro, pref);
        adoptantes.add(adoptante);
        all_adoptantes.add(adoptante);

        // BD
        adoptanteDao.insertar(adoptante);
        System.out.println("✓ Adoptante registrado.");
    }

    /**
     * Elimina el animal seleccionado.
     */
    public static void bajaAdoptante() {
        System.out.println("------");
        System.out.println("--- Baja de adoptante ---");

        // Se muestra lista de adoptantes y eligen uno
        mostrarLista(adoptantes);
        System.out.print("--> Opción: ");

        int opcion = -1;
        try {
            opcion = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor no válido");
            return;
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (opcion < 0 || opcion >= adoptantes.size()) {
            System.out.println("Índice no válido");
            return;
        }

        Adoptante aEliminar = adoptantes.get(opcion);
        aEliminar.setEliminado(true);
        adoptantes.remove(aEliminar);

        // BD
        adoptanteDao.eliminar(aEliminar.getId());
        System.out.println("✓ Adoptante borrado.");
    }

    /**
     * Permite cambiar los datos de un adoptante en alta.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void editarAdoptante() throws IOException {
        System.out.println("------");
        System.out.println("--- Editar adoptante ---");

        // Se muestra lista de adoptantes y eligen una
        mostrarLista(adoptantes);

        System.out.print("--> Editar: ");
        int opcion = -1;
        try {
            opcion = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor no válido");
            return;
        } catch (IOException e) {
            System.out.println("Error");
        }

        if (opcion < 0 || opcion >= adoptantes.size()) {
            System.out.println("Índice no válido");
            return;
        }

        // Nuevos valores
        System.out.print("DNI: ");
        String dni = br.readLine();
        System.out.print("Nombre: ");
        String nombre = br.readLine();
        System.out.print("Apellidos: ");
        String apellidos = br.readLine();
        System.out.print("Tlf: ");

        String tlf = br.readLine();
        System.out.print("Dirección: ");
        String direccion = br.readLine();
        System.out.print("Email: ");
        String email = br.readLine();
        System.out.print("Preferencia: ");
        String pref = br.readLine();

        // Sobreescribir
        Adoptante adopt = adoptantes.get(opcion);

        adopt.setDni(dni);
        adopt.setNombre(nombre);
        adopt.setApellidos(apellidos);
        adopt.setTelefono(tlf);
        adopt.setDireccion(direccion);
        adopt.setEmail(email);
        adopt.setPreferencias(pref);

        // BD
        adoptanteDao.actualizar(adopt);
        System.out.println("✓ Adoptante modificado.");
    }

    /**
     * Busca y muestra adoptante por nombre.
     *
     * @throws IOException si ocurre un error al leer la entrada
     */
    public static void buscarAdoptantePorNombre() throws IOException {
        System.out.println("------");
        System.out.println("--- Buscar adoptante por nombre ---");

        System.out.print("Nombre: ");
        String nombre = br.readLine();

        boolean encontrado = false;
        for (Adoptante adoptante : adoptantes) {
            if (adoptante.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("- Adoptante encontrado -");
                System.out.println("--> DNI: " + adoptante.getDni());
                System.out.println("--> Nombre: " + adoptante.getNombre());
                System.out.println("--> Apellidos: " + adoptante.getApellidos());
                System.out.println("--> Tlf: " + adoptante.getTelefono());
                System.out.println("--> Dirección: " + adoptante.getDireccion());
                System.out.println("--> Email: " + adoptante.getEmail());
                System.out.println("--> Registro: " + adoptante.getFechaRegistro());
                System.out.println("--> Preferencias: " + adoptante.getPreferencias());
                System.out.println("--> En refugio: " + adoptante.isEliminado());

                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha podido encontrar el animal");
        }
    }


    // MÉTODOS COMUNES

    /**
     * Muestra los elementos de una lista.
     *
     * @param lista elementos que se van a mostrar
     */
    public static void mostrarLista(List<?> lista) {
        System.out.println("------");
        System.out.println("--- Mostrar lista ---");
        for (int i = 0; i < lista.size(); i++) {
            //Object elemento = lista.get(i);
            //System.out.println(i + " - " + elemento);
            System.out.println(i + " - " + lista.get(i));
//            if (elemento instanceof Proveedor) {
//                mostrarGestionesAsociadas(((Proveedor) elemento).getGestiones());
//            } else if (elemento instanceof Pieza) {
//                mostrarGestionesAsociadas(((Pieza) elemento).getGestiones());
//            } else if (elemento instanceof Proyecto) {
//                mostrarGestionesAsociadas(((Proyecto) elemento).getGestiones());
//            }
        }
    }
}
