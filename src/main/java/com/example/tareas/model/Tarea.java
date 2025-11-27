package com.example.tareas.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa una tarea en el sistema.
 * <p>
 * Esta clase es una entidad JPA que se mapea a la tabla "tareas" en la base de datos.
 * Contiene la información básica de una tarea: título, descripción y estado de completitud.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "tareas")
public class Tarea {

    /**
     * Identificador único de la tarea.
     * <p>
     * Se genera automáticamente mediante la estrategia IDENTITY.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Título de la tarea.
     * <p>
     * Campo obligatorio que describe brevemente la tarea.
     * Debe tener entre 3 y 100 caracteres.
     * </p>
     */
    private String titulo;

    /**
     * Descripción detallada de la tarea.
     * <p>
     * Campo opcional que proporciona más información sobre la tarea.
     * Máximo 500 caracteres.
     * </p>
     */
    private String descripcion;

    /**
     * Estado de completitud de la tarea.
     * <p>
     * Indica si la tarea ha sido completada o no.
     * Por defecto es {@code false}.
     * </p>
     */
    private boolean completada = false;
}