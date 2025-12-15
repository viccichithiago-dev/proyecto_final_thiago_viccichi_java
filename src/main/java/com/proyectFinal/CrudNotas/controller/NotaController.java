package com.proyectFinal.CrudNotas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectFinal.CrudNotas.model.Nota;
import com.proyectFinal.CrudNotas.service.NotaService;

import lombok.RequiredArgsConstructor;

/**
 * Controlador REST que expone endpoints para manejar notas.
 *
 * Rutas principales bajo `/api/notas`:
 * - GET /api/notas : listar todas
 * - GET /api/notas/{id} : obtener por id
 * - POST /api/notas : crear
 * - PUT /api/notas/{id} : actualizar
 * - DELETE /api/notas/{id} : eliminar
 */
@RestController
@RequestMapping("/api/notas")
@RequiredArgsConstructor
public class NotaController {

    /** Servicio de negocio inyectado. */
    private final NotaService notaService;

    /** Devuelve la lista completa de notas. */
    @GetMapping
    public List<Nota> all() {
        return notaService.findAll();
    }

    /** Recupera una nota por su id, devolviendo 404 si no existe. */
    @GetMapping("/{id}")
    public ResponseEntity<Nota> getById(@PathVariable Long id) {
        return notaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Crea una nueva nota. Retorna 201 con la ubicación en el header Location. */
    @PostMapping
    public ResponseEntity<Nota> create(@RequestBody Nota nota) {
        if (nota.getId() != null) {
            // Evitar que el cliente asigne un ID al crear una nueva nota
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
        Nota saved = notaService.save(nota);
        return ResponseEntity.created(URI.create("/api/notas/" + saved.getId())).body(saved);
    }

    /** Actualiza una nota por id. */
    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Long id, @RequestBody Nota nota) {
        if (nota.getId() != null && !nota.getId().equals(id)) {
            // Evitar inconsistencias entre el ID de la URL y el del cuerpo
            return ResponseEntity.badRequest().build(); // 400 Bad Request
            
        }
        Nota updated = notaService.update(id, nota);
        return ResponseEntity.ok(updated);
    }

    /** Elimina una nota por id. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!notaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
            
        }
        notaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
