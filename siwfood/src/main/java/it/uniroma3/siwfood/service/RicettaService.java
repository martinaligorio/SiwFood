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

    public Ricetta findById(Long id) {
        return ricettaRepository.findById(id).get();
    }

    public Iterable<Ricetta> findAll() {
        return ricettaRepository.findAll();
    }

   // public List<Ricetta> findByCuochi(String cuoco) {
//    return ricettaRepository.findByCuochi(cuoco);
   // }
 
    public void save(Ricetta ricetta) {
        ricettaRepository.save(ricetta);
    }

}    
