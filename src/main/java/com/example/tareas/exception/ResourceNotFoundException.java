package com.example.tareas.exception;

/**
 * Excepción personalizada para indicar que un recurso no fue encontrado.
 * <p>
 * Esta excepción se lanza cuando se intenta acceder a un recurso
 * que no existe en el sistema. Típicamente resulta en un código
 * de estado HTTP 404 (Not Found).
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see RuntimeException
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Crea una nueva excepción con un mensaje personalizado.
     *
     * @param message mensaje descriptivo del error
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción indicando el tipo de recurso y su identificador.
     *
     * @param resource nombre del tipo de recurso no encontrado
     * @param id identificador del recurso buscado
     */
    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s con ID %d no encontrado", resource, id));
    }
}