package com.example.tareas.model;

import jakarta.persistence.*;
import lombok. Data;

@Data
@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType. IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    private boolean completada = false;
}