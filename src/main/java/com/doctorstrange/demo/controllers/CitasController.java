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

import com.doctorstrange.demo.entities.Citas;
import com.doctorstrange.demo.repositories.CitasRepository;

@RestController
@RequestMapping(value = "/citas")
public class CitasController {

    @Autowired
    CitasRepository citasRepository;
    
    @GetMapping
    @ResponseStatus(code =  HttpStatus.OK)
    public Collection<Citas> getCitas(){
        Iterable<Citas> citas = citasRepository.findAll();
        return (Collection<Citas>) citas;
    }
    
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Citas getCitas(@PathVariable(name = "id") Long id) {
		
		Optional<Citas> citas = citasRepository.findById(id);
		Citas result = null;
		if(citas.isPresent()) {
			result = citas.get();
		}
		return result;	
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Citas createCitas(@RequestBody Citas citas ) {
		Citas nuevaCita = citasRepository.save(citas);
		return nuevaCita;
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Citas updateCita(@PathVariable(name = "id") Long id, 
			@RequestBody Citas citas) {
		Optional<Citas> oCita = citasRepository.findById(id);
		if(oCita.isPresent()) {
			Citas actual = oCita.get(); 
			actual.setId(id);
			actual.setFecha(citas.getFecha());
			actual.setHora(citas.getHora());
			actual.setPaciente(citas.getPaciente());
			actual.setEstado(citas.getEstado());
			actual.setObservaciones(citas.getObservaciones());
			Citas updatedCita = citasRepository.save(actual);
			return updatedCita;
		}
		return null;
	}
	
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCita(@PathVariable(name = "id") Long id){
        citasRepository.deleteById(id);
    }
}
