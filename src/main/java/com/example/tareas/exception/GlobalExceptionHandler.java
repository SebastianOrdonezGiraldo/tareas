package com.example.tareas.exception;

import com.example.tareas.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Manejador global de excepciones para la API REST.
 * <p>
 * Esta clase intercepta las excepciones lanzadas por los controladores
 * y las convierte en respuestas HTTP apropiadas con información de error
 * estructurada. Utiliza {@link RestControllerAdvice} para aplicar el
 * manejo de excepciones a todos los controladores REST.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see RestControllerAdvice
 * @see ErrorResponse
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger para registrar los errores capturados.
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja las excepciones de tipo {@link ResourceNotFoundException}.
     * <p>
     * Retorna un código de estado HTTP 404 (Not Found) con los detalles del error.
     * </p>
     *
     * @param ex excepción capturada
     * @param request información de la solicitud web
     * @return ResponseEntity con el error y código HTTP 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        logger.error("Recurso no encontrado: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false). replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja las excepciones de tipo {@link BadRequestException}.
     * <p>
     * Retorna un código de estado HTTP 400 (Bad Request) con los detalles del error.
     * </p>
     *
     * @param ex excepción capturada
     * @param request información de la solicitud web
     * @return ResponseEntity con el error y código HTTP 400
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            BadRequestException ex, WebRequest request) {

        logger.error("Solicitud incorrecta: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja las excepciones de tipo {@link IllegalArgumentException}.
     * <p>
     * Retorna un código de estado HTTP 400 (Bad Request) con los detalles del error.
     * </p>
     *
     * @param ex excepción capturada
     * @param request información de la solicitud web
     * @return ResponseEntity con el error y código HTTP 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        logger.error("Argumento ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja todas las excepciones no capturadas por otros manejadores.
     * <p>
     * Actúa como manejador de último recurso para cualquier excepción
     * no prevista. Retorna un código de estado HTTP 500 (Internal Server Error).
     * </p>
     *
     * @param ex excepción capturada
     * @param request información de la solicitud web
     * @return ResponseEntity con el error y código HTTP 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {

        logger.error("Error interno del servidor: ", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ha ocurrido un error inesperado en el servidor",
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}