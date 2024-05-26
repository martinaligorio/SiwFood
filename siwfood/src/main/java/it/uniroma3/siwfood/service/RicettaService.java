package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Cuoco;
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

    public List<Ricetta> findByCuoco(Cuoco cuoco) {
        return ricettaRepository.findByCuoco(cuoco);
    }
 
    public void save(Ricetta ricetta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}    
