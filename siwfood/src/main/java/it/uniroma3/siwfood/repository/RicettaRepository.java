package it.uniroma3.siwfood.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta,Long>{
    
   //cercare ricette per nome 
   //@Query("SELECT r FROM Ricetta r WHERE lower(r.nome) like lower(concat('%', :nome, '%'))")  //Cerca il parametro nome all'interno del campo nome dell'entità Cuoco, ignorando maiuscole e minuscole e cercando corrispondenze parziali.
   List<Ricetta> findByNome(String nome);
}
