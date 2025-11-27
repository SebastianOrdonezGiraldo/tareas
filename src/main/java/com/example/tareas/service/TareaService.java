package com. example.tareas.service;

import com.example.tareas. exception.BadRequestException;
import com.example.tareas.exception.ResourceNotFoundException;
import com.example.tareas.model.Tarea;
import com.example.tareas.repository.TareaRepository;
import org.slf4j. Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util. List;

@Service
@Transactional
public class TareaService {

    private static final Logger logger = LoggerFactory.getLogger(TareaService.class);
    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public List<Tarea> listar() {
        logger.info("Listando todas las tareas");
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Error al listar tareas", e);
            throw new RuntimeException("Error al obtener la lista de tareas", e);
        }
    }

    public Tarea crear(Tarea tarea) {
        logger.info("Creando nueva tarea: {}", tarea != null ? tarea.getTitulo() : "null");

        // Validaciones básicas
        validarTarea(tarea);

        try {
            Tarea tareaGuardada = repository.save(tarea);
            logger.info("Tarea creada exitosamente con ID: {}", tareaGuardada.getId());
            return tareaGuardada;
        } catch (Exception e) {
            logger.error("Error al crear tarea", e);
            throw new RuntimeException("Error al crear la tarea", e);
        }
    }

    public Tarea obtener(Long id) {
        logger.info("Obteniendo tarea con ID: {}", id);

        validarId(id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tarea no encontrada con ID: {}", id);
                    return new ResourceNotFoundException("Tarea", id);
                });
    }

    public Tarea actualizar(Long id, Tarea datos) {
        logger.info("Actualizando tarea con ID: {}", id);

        validarId(id);
        validarTarea(datos);

        Tarea tarea = obtener(id); // Lanza excepción si no existe

        try {
            tarea.setTitulo(datos.getTitulo());
            tarea.setDescripcion(datos.getDescripcion());
            tarea.setCompletada(datos.isCompletada());

            Tarea tareaActualizada = repository.save(tarea);
            logger.info("Tarea actualizada exitosamente con ID: {}", id);
            return tareaActualizada;
        } catch (Exception e) {
            logger.error("Error al actualizar tarea con ID: {}", id, e);
            throw new RuntimeException("Error al actualizar la tarea", e);
        }
    }

    public void eliminar(Long id) {
        logger.info("Eliminando tarea con ID: {}", id);

        validarId(id);

        // Verificar que existe antes de eliminar
        obtener(id);

        try {
            repository.deleteById(id);
            logger.info("Tarea eliminada exitosamente con ID: {}", id);
        } catch (Exception e) {
            logger.error("Error al eliminar tarea con ID: {}", id, e);
            throw new RuntimeException("Error al eliminar la tarea", e);
        }
    }

    // ========== MÉTODOS DE VALIDACIÓN BÁSICA ==========

    private void validarId(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser nulo");
        }
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser un número positivo");
        }
    }

    private void validarTarea(Tarea tarea) {
        if (tarea == null) {
            throw new BadRequestException("La tarea no puede ser nula");
        }

        // Validar título
        if (tarea.getTitulo() == null || tarea.getTitulo().trim().isEmpty()) {
            throw new BadRequestException("El título de la tarea es obligatorio");
        }

        if (tarea.getTitulo().trim().length() < 3) {
            throw new BadRequestException("El título debe tener al menos 3 caracteres");
        }

        if (tarea.getTitulo(). length() > 100) {
            throw new BadRequestException("El título no puede exceder los 100 caracteres");
        }

        // Validar descripción (opcional pero con límite)
        if (tarea.getDescripcion() != null && tarea.getDescripcion().length() > 500) {
            throw new BadRequestException("La descripción no puede exceder los 500 caracteres");
        }
    }
}