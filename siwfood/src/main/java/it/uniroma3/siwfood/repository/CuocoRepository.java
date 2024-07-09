package it.uniroma3.siwfood.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;


import it.uniroma3.siwfood.model.Cuoco;


public interface CuocoRepository extends CrudRepository<Cuoco,Long>{
    
    //cercare cuochi per nome 
    //@Query("SELECT c FROM Cuoco c WHERE lower(c.nome) like lower(concat('%', :nome, '%'))")  //Cerca il parametro nome all'interno del campo nome dell'entità Cuoco, ignorando maiuscole e minuscole e cercando corrispondenze parziali.
    public List<Cuoco> findByNome(String nome);

    public boolean existsByNomeAndCognome(String nome, String cognome);

    public Cuoco findByNomeAndCognome(String nome, String cognome);
}
