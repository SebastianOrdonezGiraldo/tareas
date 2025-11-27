package com.example.tareas.service;

import com.example.tareas.exception.BadRequestException;
import com.example.tareas.exception.ResourceNotFoundException;
import com.example.tareas.model.Tarea;
import com.example.tareas.repository.TareaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de negocio para la gestión de tareas.
 * <p>
 * Esta clase proporciona la lógica de negocio para las operaciones CRUD
 * sobre las tareas. Incluye validaciones de datos y manejo de transacciones.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see TareaRepository
 * @see Tarea
 */
@Service
@Transactional
public class TareaService {

    /**
     * Logger para registrar eventos y mensajes del servicio.
     */
    private static final Logger logger = LoggerFactory.getLogger(TareaService.class);

    /**
     * Repositorio para acceder a los datos de las tareas.
     */
    private final TareaRepository repository;

    /**
     * Constructor que inyecta el repositorio de tareas.
     *
     * @param repository repositorio JPA para operaciones de persistencia
     */
    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todas las tareas almacenadas en el sistema.
     *
     * @return lista de todas las tareas
     * @throws RuntimeException si ocurre un error al acceder a la base de datos
     */
    public List<Tarea> listar() {
        logger.info("Listando todas las tareas");
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Error al listar tareas", e);
            throw new RuntimeException("Error al obtener la lista de tareas", e);
        }
    }

    /**
     * Crea una nueva tarea en el sistema.
     * <p>
     * Valida los datos de la tarea antes de persistirla en la base de datos.
     * </p>
     *
     * @param tarea objeto Tarea con los datos a guardar
     * @return la tarea creada con su ID asignado
     * @throws BadRequestException si los datos de la tarea no son válidos
     * @throws RuntimeException si ocurre un error al guardar en la base de datos
     */
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

    /**
     * Obtiene una tarea por su identificador.
     *
     * @param id identificador único de la tarea
     * @return la tarea encontrada
     * @throws BadRequestException si el ID es nulo o no positivo
     * @throws ResourceNotFoundException si la tarea no existe
     */
    public Tarea obtener(Long id) {
        logger.info("Obteniendo tarea con ID: {}", id);

        validarId(id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tarea no encontrada con ID: {}", id);
                    return new ResourceNotFoundException("Tarea", id);
                });
    }

    /**
     * Actualiza una tarea existente con nuevos datos.
     * <p>
     * Valida los datos proporcionados y actualiza el título, descripción
     * y estado de completitud de la tarea.
     * </p>
     *
     * @param id identificador único de la tarea a actualizar
     * @param datos objeto Tarea con los nuevos datos
     * @return la tarea actualizada
     * @throws BadRequestException si el ID o los datos no son válidos
     * @throws ResourceNotFoundException si la tarea no existe
     * @throws RuntimeException si ocurre un error al actualizar en la base de datos
     */
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

    /**
     * Elimina una tarea del sistema.
     *
     * @param id identificador único de la tarea a eliminar
     * @throws BadRequestException si el ID es nulo o no positivo
     * @throws ResourceNotFoundException si la tarea no existe
     * @throws RuntimeException si ocurre un error al eliminar de la base de datos
     */
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

    /**
     * Valida que el ID proporcionado sea válido.
     *
     * @param id identificador a validar
     * @throws BadRequestException si el ID es nulo o menor o igual a cero
     */
    private void validarId(Long id) {
        if (id == null) {
            throw new BadRequestException("El ID no puede ser nulo");
        }
        if (id <= 0) {
            throw new BadRequestException("El ID debe ser un número positivo");
        }
    }

    /**
     * Valida que los datos de la tarea sean correctos.
     * <p>
     * Reglas de validación:
     * </p>
     * <ul>
     *   <li>La tarea no puede ser nula</li>
     *   <li>El título es obligatorio (3-100 caracteres)</li>
     *   <li>La descripción es opcional (máximo 500 caracteres)</li>
     * </ul>
     *
     * @param tarea objeto Tarea a validar
     * @throws BadRequestException si alguna validación falla
     */
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