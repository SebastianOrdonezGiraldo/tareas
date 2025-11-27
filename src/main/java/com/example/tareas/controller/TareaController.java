package com.example.tareas.controller;

import com.example.tareas.model.Tarea;
import com.example.tareas.service.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de tareas.
 * <p>
 * Proporciona endpoints para realizar operaciones CRUD sobre las tareas
 * a través de la API REST. Todos los endpoints están bajo la ruta base
 * {@code /api/tareas}.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see TareaService
 * @see Tarea
 */
@RestController
@RequestMapping("/api/tareas")
@CrossOrigin("*")
public class TareaController {

    /**
     * Logger para registrar eventos y mensajes del controlador.
     */
    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

    /**
     * Servicio de tareas para realizar las operaciones de negocio.
     */
    private final TareaService service;

    /**
     * Constructor que inyecta el servicio de tareas.
     *
     * @param service servicio de tareas para las operaciones de negocio
     */
    public TareaController(TareaService service) {
        this.service = service;
    }

    /**
     * Lista todas las tareas disponibles.
     * <p>
     * Endpoint: {@code GET /api/tareas}
     * </p>
     *
     * @return ResponseEntity con la lista de tareas y código HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Tarea>> listar() {
        logger.info("GET /api/tareas - Listando todas las tareas");
        List<Tarea> tareas = service.listar();
        return ResponseEntity.ok(tareas);
    }

    /**
     * Crea una nueva tarea en el sistema.
     * <p>
     * Endpoint: {@code POST /api/tareas}
     * </p>
     *
     * @param tarea objeto Tarea con los datos de la nueva tarea
     * @return ResponseEntity con la tarea creada y código HTTP 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        logger.info("POST /api/tareas - Creando nueva tarea");
        Tarea tareaCreada = service.crear(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
    }

    /**
     * Obtiene una tarea específica por su identificador.
     * <p>
     * Endpoint: {@code GET /api/tareas/{id}}
     * </p>
     *
     * @param id identificador único de la tarea a obtener
     * @return ResponseEntity con la tarea encontrada y código HTTP 200 (OK)
     * @throws com.example.tareas.exception.ResourceNotFoundException si la tarea no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtener(@PathVariable Long id) {
        logger.info("GET /api/tareas/{} - Obteniendo tarea", id);
        Tarea tarea = service.obtener(id);
        return ResponseEntity.ok(tarea);
    }

    /**
     * Actualiza una tarea existente.
     * <p>
     * Endpoint: {@code PUT /api/tareas/{id}}
     * </p>
     *
     * @param id identificador único de la tarea a actualizar
     * @param tarea objeto Tarea con los nuevos datos
     * @return ResponseEntity con la tarea actualizada y código HTTP 200 (OK)
     * @throws com.example.tareas.exception.ResourceNotFoundException si la tarea no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(
            @PathVariable Long id,
            @RequestBody Tarea tarea) {
        logger.info("PUT /api/tareas/{} - Actualizando tarea", id);
        Tarea tareaActualizada = service.actualizar(id, tarea);
        return ResponseEntity.ok(tareaActualizada);
    }

    /**
     * Elimina una tarea del sistema.
     * <p>
     * Endpoint: {@code DELETE /api/tareas/{id}}
     * </p>
     *
     * @param id identificador único de la tarea a eliminar
     * @return ResponseEntity vacío con código HTTP 204 (No Content)
     * @throws com.example.tareas.exception.ResourceNotFoundException si la tarea no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("DELETE /api/tareas/{} - Eliminando tarea", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}