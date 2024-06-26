package it.uniroma3.siwfood.repository;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente,Long>{

    boolean existsByNomeAndQuantita(String nome, String quantita);
    
}
