package com.example.tareas.exception;

/**
 * Excepción personalizada para indicar solicitudes incorrectas o inválidas.
 * <p>
 * Esta excepción se lanza cuando los datos proporcionados por el cliente
 * no cumplen con los requisitos de validación o formato esperados.
 * Típicamente resulta en un código de estado HTTP 400 (Bad Request).
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see RuntimeException
 */
public class BadRequestException extends RuntimeException {

    /**
     * Crea una nueva excepción de solicitud incorrecta con el mensaje especificado.
     *
     * @param message mensaje descriptivo del error de validación
     */
    public BadRequestException(String message) {
        super(message);
    }
}