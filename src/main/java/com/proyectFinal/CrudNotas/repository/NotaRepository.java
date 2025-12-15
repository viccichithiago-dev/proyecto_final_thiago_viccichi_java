
package com.proyectFinal.CrudNotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectFinal.CrudNotas.model.Nota;

/**
 * Repositorio Spring Data JPA para la entidad `Nota`.
 * Extiende JpaRepository para obtener métodos CRUD listos para usar
 * (findAll, findById, save, deleteById, etc.).
 */
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
