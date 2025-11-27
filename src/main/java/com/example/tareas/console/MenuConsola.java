package com.example.tareas.console;

import com.example.tareas.model.Tarea;
import com.example.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {

    private final TareaService tareaService;

    public MenuConsola(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========== MENÚ DE TAREAS ==========");
            System.out.println("1. Crear tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Actualizar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    Tarea nueva = new Tarea();
                    nueva.setTitulo(titulo);
                    nueva.setDescripcion(descripcion);

                    tareaService.crear(nueva);
                    System.out.println("✔ Tarea creada exitosamente.");
                }

                case 2 -> {
                    List<Tarea> tareas = tareaService.listar();
                    System.out.println("\n--- LISTA DE TAREAS ---");
                    tareas.forEach(t -> {
                        System.out.println("ID: " + t.getId());
                        System.out.println("Título: " + t.getTitulo());
                        System.out.println("Descripción: " + t.getDescripcion());
                        System.out.println("Completada: " + (t.isCompletada() ? "Sí" : "No"));
                        System.out.println("-------------------------");
                    });
                }

                case 3 -> {
                    System.out.print("ID de la tarea a actualizar: ");
                    long id = sc.nextLong();
                    sc.nextLine();

                    Tarea tarea = tareaService.obtener(id);

                    if (tarea == null) {
                        System.out.println("❌ Tarea no encontrada.");
                        break;
                    }

                    System.out.print("Nuevo título: ");
                    String nuevoTitulo = sc.nextLine();

                    System.out.print("Nueva descripción: ");
                    String nuevaDescripcion = sc.nextLine();

                    System.out.print("¿Completada? (true/false): ");
                    boolean completada = sc.nextBoolean();

                    Tarea datos = new Tarea();
                    datos.setTitulo(nuevoTitulo);
                    datos.setDescripcion(nuevaDescripcion);
                    datos.setCompletada(completada);

                    tareaService.actualizar(id, datos);

                    System.out.println("✔ Tarea actualizada.");
                }

                case 4 -> {
                    System.out.print("ID de la tarea a eliminar: ");
                    long idEliminar = sc.nextLong();

                    tareaService.eliminar(idEliminar);
                    System.out.println("✔ Tarea eliminada.");
                }

                case 5 ->
                        System.out.println("Saliendo... 👋");

                default ->
                        System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 5);
    }
}
