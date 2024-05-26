package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.repository.CuocoRepository;

@Service 
public class CuocoService {

    @Autowired 
    private CuocoRepository cuocoRepository; 

    public Cuoco findById(Long id) {
        return cuocoRepository.findById(id).get();
    }

    public Iterable<Cuoco> findAll() {
        return cuocoRepository.findAll();
    }

    public List<Cuoco> findByName(String nome) {
        return cuocoRepository.findByNome(nome);
    }
    
    public void save(Cuoco artista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}    
