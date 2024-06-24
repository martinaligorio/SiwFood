package it.uniroma3.siwfood.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco,Long>{
    
    //public List<Cuoco> findByRicette(String ricette);
    
    public boolean existsByNome(String nome);
}
