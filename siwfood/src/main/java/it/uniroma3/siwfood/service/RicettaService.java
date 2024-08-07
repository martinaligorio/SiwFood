package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.repository.RicettaRepository;

@Service 
public class RicettaService {

    @Autowired 
    private RicettaRepository ricettaRepository; 

    @Autowired 
    private CuocoService cuocoService; 

    public Ricetta findById(Long id) {
        return ricettaRepository.findById(id).get();
    }

    public Iterable<Ricetta> findAll() {
        return ricettaRepository.findAll();
    }

    public Ricetta save(Ricetta ricetta) {
        return ricettaRepository.save(ricetta);
    }

    public List<Ricetta> findByNome(String nome) {
        return ricettaRepository.findByNome(nome);
    }

    public Iterable<Ricetta> findByIngredienteNome(String nomeIngrediente){
        return ricettaRepository.findByIngredienteNome(nomeIngrediente);
    }

    public void deleteRicettaById(Long id) {
        ricettaRepository.deleteById(id);
    }

    public void addRicettaToCuoco(Ricetta ricetta,Long cuocoid){
        ricetta.setCuoco(this.cuocoService.findById(cuocoid));
        this.save(ricetta);
    }
}    
