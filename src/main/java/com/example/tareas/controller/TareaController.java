package com.example.tareas.controller;

import com.example.tareas.model.Tarea;
import com.example.tareas.service. TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org. springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin("*")
public class TareaController {

    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);
    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listar() {
        logger.info("GET /api/tareas - Listando todas las tareas");
        List<Tarea> tareas = service.listar();
        return ResponseEntity.ok(tareas);
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        logger.info("POST /api/tareas - Creando nueva tarea");
        Tarea tareaCreada = service.crear(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaCreada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtener(@PathVariable Long id) {
        logger.info("GET /api/tareas/{} - Obteniendo tarea", id);
        Tarea tarea = service.obtener(id);
        return ResponseEntity.ok(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(
            @PathVariable Long id,
            @RequestBody Tarea tarea) {
        logger.info("PUT /api/tareas/{} - Actualizando tarea", id);
        Tarea tareaActualizada = service.actualizar(id, tarea);
        return ResponseEntity. ok(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logger.info("DELETE /api/tareas/{} - Eliminando tarea", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}