package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Ingrediente;
import it.uniroma3.siwfood.repository.IngredienteRepository;

@Service 
public class IngredienteService {

    @Autowired 
    private IngredienteRepository ingredienteRepository; 

    public Ingrediente findById(Long id) {
        return ingredienteRepository.findById(id).get();
    }

    public Iterable<Ingrediente> findAll() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente save(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void deleteIngredienteById(Long id) {
        ingredienteRepository.deleteById(id);
    }
}    
