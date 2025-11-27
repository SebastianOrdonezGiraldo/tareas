package com.example.tareas.repository;

import com.example.tareas.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Tarea}.
 * <p>
 * Esta interfaz extiende {@link JpaRepository} y proporciona métodos CRUD
 * estándar para la gestión de tareas en la base de datos. Spring Data JPA
 * genera automáticamente la implementación en tiempo de ejecución.
 * </p>
 * <p>
 * Métodos heredados incluyen:
 * </p>
 * <ul>
 *   <li>{@code save()} - Guardar o actualizar una tarea</li>
 *   <li>{@code findById()} - Buscar tarea por ID</li>
 *   <li>{@code findAll()} - Listar todas las tareas</li>
 *   <li>{@code deleteById()} - Eliminar tarea por ID</li>
 * </ul>
 *
 * @author Desarrollador
 * @version 1.0.0
 * @since 1.0.0
 * @see JpaRepository
 * @see Tarea
 */
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
