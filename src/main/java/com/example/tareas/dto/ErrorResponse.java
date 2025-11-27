package com.example.tareas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase DTO (Data Transfer Object) para representar respuestas de error.
 * <p>
 * Esta clase encapsula la información de error que se devuelve al cliente
 * cuando ocurre una excepción en la aplicación. Incluye detalles como
 * la marca de tiempo, código de estado HTTP, mensaje de error y la ruta
 * donde ocurrió el error.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * Marca de tiempo que indica cuándo ocurrió el error.
     */
    private LocalDateTime timestamp;

    /**
     * Código de estado HTTP del error.
     */
    private int status;

    /**
     * Tipo de error (ej: "Not Found", "Bad Request").
     */
    private String error;

    /**
     * Mensaje descriptivo del error.
     */
    private String message;

    /**
     * Ruta del endpoint donde ocurrió el error.
     */
    private String path;

    /**
     * Lista de detalles adicionales sobre el error.
     */
    private List<String> details;

    /**
     * Constructor para crear una respuesta de error sin detalles adicionales.
     *
     * @param timestamp marca de tiempo del error
     * @param status código de estado HTTP
     * @param error tipo de error
     * @param message mensaje descriptivo del error
     * @param path ruta donde ocurrió el error
     */
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}