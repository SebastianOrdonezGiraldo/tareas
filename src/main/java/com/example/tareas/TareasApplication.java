package com.example.tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación de gestión de tareas.
 * <p>
 * Esta clase sirve como punto de entrada para la aplicación Spring Boot.
 * Configura y arranca el contexto de la aplicación, habilitando la
 * autoconfiguración de Spring Boot y el escaneo de componentes.
 * </p>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootApplication
public class TareasApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 * <p>
	 * Este método configura y ejecuta la aplicación utilizando
	 * {@link SpringApplication#run(Class, String...)}.
	 * </p>
	 *
	 * @param args argumentos de línea de comandos pasados a la aplicación
	 */
	public static void main(String[] args) {
		SpringApplication.run(TareasApplication.class, args);
	}
}
