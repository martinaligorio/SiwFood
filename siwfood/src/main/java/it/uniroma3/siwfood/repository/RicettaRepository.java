package it.uniroma3.siwfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwfood.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta,Long>{
    
   List<Ricetta> findByNome(String nome);

   // Metodo per trovare le ricette che contengono un determinato ingrediente
    @Query("SELECT r FROM Ricetta r JOIN r.ingredienti i WHERE i.nome = :nomeIngrediente")
    public Iterable<Ricetta> findByIngredienteNome(@Param("nomeIngrediente") String nomeIngrediente);
}
