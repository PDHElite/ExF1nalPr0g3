package com.doctorstrange.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.doctorstrange.demo.entities.Pacientes;

public interface PacientesRepository extends CrudRepository<Pacientes, Long> {

}
