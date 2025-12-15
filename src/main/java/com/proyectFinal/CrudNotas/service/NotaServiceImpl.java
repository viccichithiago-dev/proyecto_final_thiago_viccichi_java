package com.proyectFinal.CrudNotas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.proyectFinal.CrudNotas.model.Nota;
import com.proyectFinal.CrudNotas.repository.NotaRepository;

/**
 * Implementación del servicio de notas.
 * Utiliza `NotaRepository` para realizar operaciones de persistencia.
 */
@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    /** Repositorio inyectado (constructor generado por Lombok @RequiredArgsConstructor). */
    private final NotaRepository notaRepository;

    /** Devuelve todas las notas. */
    @Override
    public List<Nota> findAll() {
        return notaRepository.findAll();
    }

    /** Busca una nota por id. */
    @Override
    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    /** Guarda una nueva nota o actualiza si tiene id. */
    @Override
    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    /**
     * Actualiza la nota existente; si no existe, crea una nueva con el id dado.
     * La lógica de actualizar copia sólo título y contenido.
     */
    @Override
    public Nota update(Long id, Nota nota) {
        return notaRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(nota.getTitulo());
                    existing.setContenido(nota.getContenido());
                    return notaRepository.save(existing);
                })
                .orElseGet(() -> {
                    nota.setId(id);
                    return notaRepository.save(nota);
                });
    }

    /** Elimina la nota por id. */
    @Override
    public void deleteById(Long id) {
        notaRepository.deleteById(id);
    }
}
