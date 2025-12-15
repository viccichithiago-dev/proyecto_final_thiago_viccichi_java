package com.proyectFinal.CrudNotas.service;

import java.util.List;
import java.util.Optional;

import com.proyectFinal.CrudNotas.model.Nota;

/**
 * Interfaz del servicio de negocio para manejar operaciones sobre notas.
 *
 * Separar la interfaz de la implementación facilita pruebas unitarias
 * y permite cambiar la implementación sin afectar consumidores.
 */
public interface NotaService {
    /** Devuelve todas las notas. */
    List<Nota> findAll();

    /** Busca una nota por su id. */
    Optional<Nota> findById(Long id);

    /** Crea/guarda una nueva nota. */
    Nota save(Nota nota);

    /** Actualiza una nota existente por id. Si no existe, la crea con ese id. */
    Nota update(Long id, Nota nota);

    /** Elimina una nota por id. */
    void deleteById(Long id);
}
