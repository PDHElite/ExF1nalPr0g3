package com.doctorstrange.demo.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doctorstrange.demo.entities.Pacientes;
import com.doctorstrange.demo.repositories.PacientesRepository;

@RestController
@RequestMapping(value = "/pacientes")
public class PacientesControllers {
	
    @Autowired
    PacientesRepository pacientesRepository;

    @GetMapping
    @ResponseStatus(code =  HttpStatus.OK)
    public Collection<Pacientes> getPacientes(){
        Iterable<Pacientes> pacientes = pacientesRepository.findAll();
        return (Collection<Pacientes>) pacientes;
    }
    
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pacientes getPacientes(@PathVariable(name = "id") Long id) {
		
		Optional<Pacientes> paciente = pacientesRepository.findById(id);
		Pacientes result = null;
		if(paciente.isPresent()) {
			result = paciente.get();
		}
		return result;	
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pacientes createPaciente(@RequestBody Pacientes paciente ) {
		Pacientes nuevoPaciente = pacientesRepository.save(paciente);
		return nuevoPaciente;
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Pacientes updatePaciente(@PathVariable(name = "id") Long id, 
			@RequestBody Pacientes paciente) {
		Optional<Pacientes> oPaciente = pacientesRepository.findById(id);
		if(oPaciente.isPresent()) {
			Pacientes actual = oPaciente.get(); 
			actual.setId(id);
			actual.setPrimernombre(paciente.getPrimernombre());
			actual.setSegundonombre(paciente.getSegundonombre());
			actual.setApellido(paciente.getApellido());
			actual.setSegundoapellido(paciente.getSegundoapellido());
			actual.setEdad(paciente.getEdad());
			Pacientes updatedPaciente = pacientesRepository.save(actual);
			return updatedPaciente;
		}
		return null;
	}
	
	
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deletePaciente(@PathVariable(name = "id") Long id){
        pacientesRepository.deleteById(id);
    }

}
