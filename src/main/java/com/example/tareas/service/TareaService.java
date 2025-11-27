package com.example.tareas.service;

import com.example.tareas.model.Tarea;
import com.example.tareas.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository repository;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public List<Tarea> listar() {
        return repository.findAll();
    }

    public Tarea crear(Tarea tarea) {
        return repository.save(tarea);
    }

    public Tarea obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tarea actualizar(Long id, Tarea datos) {
        Tarea tarea = obtener(id);
        if (tarea != null) {
            tarea.setTitulo(datos.getTitulo());
            tarea.setDescripcion(datos.getDescripcion());
            tarea.setCompletada(datos.isCompletada());
            return repository.save(tarea);
        }
        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
