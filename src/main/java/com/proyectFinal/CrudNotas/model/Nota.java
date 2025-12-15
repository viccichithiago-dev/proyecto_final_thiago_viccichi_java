package com.proyectFinal.CrudNotas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA que representa una nota en la aplicación.
 *
 * Campos principales:
 * - id: clave primaria generada automáticamente.
 * - titulo: título corto de la nota.
 * - contenido: texto de la nota (almacenado como TEXT en la BD).
 * - creadoEn: fecha/hora de creación; se inicializa antes de persistir.
 */
@Entity
@Table(name = "notas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private LocalDateTime creadoEn;
    /**
     * Método anotado con @PrePersist que se ejecuta justo antes
     * de que la entidad se persista en la base de datos.
     * Aquí se asegura que `creadoEn` tenga un valor por defecto.
     */
    @PrePersist
    public void prePersist() {
        if (creadoEn == null) {
            creadoEn = LocalDateTime.now();
        }
    }
}
